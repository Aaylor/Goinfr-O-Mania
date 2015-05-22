package engine;

import log.IGLog;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {

    private List<Score> scores;
    private String path;

    private boolean changed = false;

    public Scores(String path) {
        this.path = path;
        scores = new ArrayList<>();
        load();
    }

    public void addScore(String name, Score s) {
        changed = true;
        Score score = new Score(name, s.getValue());
        IGLog.write("Scores::addScores -> " + score);
        scores.add(score);
    }

    private void load() {

        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document document;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            e.printStackTrace();
            IGLog.error("Scores::load -> fail to load scores.");
            return;
        }

        document.getDocumentElement().normalize();

        NodeList nl = document.getElementsByTagName("score");

        if (nl.getLength() != 10) {
            IGLog.error("Scores::load -> invalid file.");
            return;
        }

        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String player =
                        element.getElementsByTagName("player").item(0).getTextContent();

                int score = Integer.valueOf(
                        element.getElementsByTagName("value").item(0).getTextContent()
                );

                scores.add(new Score(player, score));
            } else {
                IGLog.error("Scores::load -> [" + i + "] node error.");
            }
        }

        IGLog.info("Scores::load() -> end of loading.");
    }

    public void save() {
        int cpt;

        /*
        if (!changed)
            return;
        */

        for (Score score : scores) {
            System.out.println(score.getWho() + " -- " + score.getValue());
        }

        IGLog.write("Scores::save -> exit detected + changes");
        Collections.sort(scores, new Score());

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            IGLog.error("Scores::save -> fail to create a document builder.");
            return;
        }

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("scores");
        doc.appendChild(rootElement);

        cpt = 0;
        for (Score s : scores) {
            if (cpt >= 10)
                break;

            Element score = doc.createElement("score");

            Element player = doc.createElement("player");
            player.setAttribute("id", String.valueOf(cpt));
            player.appendChild(doc.createTextNode(s.getWho()));

            Element value = doc.createElement("value");
            value.appendChild(doc.createTextNode(String.valueOf(s.getValue())));

            score.appendChild(player);
            score.appendChild(value);
            System.out.println("{ " + s.getWho() + " ; " + s.getValue() + " }");
            rootElement.appendChild(score);
            ++cpt;
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            IGLog.error("Scores::save() -> fail to create transformer.");
            return;
        }

        DOMSource source = new DOMSource(doc);

        StreamResult r = new StreamResult(new File(path));

        try {
            transformer.transform(source, r);
        } catch (TransformerException e) {
            e.printStackTrace();
            IGLog.error("Scores::save() -> fail to transform.");
            return;
        }
    }

}
