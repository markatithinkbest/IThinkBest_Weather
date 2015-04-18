package com.example.android.networkusage;

import android.util.Log;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author MARK
 */
public class XmlDomParser {
    public static final String LOG_TAG = "MARK987";
    private static final String URL2 =
            "http://opendata.cwb.gov.tw/opendataapi?dataid=F-C0032-005&authorizationkey=CWB-B2CA193D-2DE6-4121-AF6A-BFA9C12713BF";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static void parse(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse("C:\\xmlfiles\\F-C0032-005.xml");
            Document doc = builder.parse(URL2);

            Log.d(LOG_TAG, "...DOING PARSE...");

            NodeList dataInfoList = doc.getElementsByTagName("datasetInfo");
            for (int x = 0; x < dataInfoList.getLength(); x++) {
                Node dataInfoNode = dataInfoList.item(x);// only one
                if (dataInfoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e1 = (Element) dataInfoNode;
//                    System.out.println("..." + e1.getTagName() + " " + e1.getTextContent());
                    System.out.println("..." + e1.getTagName());
                    NodeList e2List = e1.getChildNodes();
                    for (int x2 = 0; x2 < e2List.getLength(); x2++) {
                        Node e2Node = e2List.item(x2);
                        if (e2Node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e2 = (Element) e2Node;
                            System.out.println("......" + e2.getTagName() + " " + e2.getTextContent());
                        }
                    }
                }

            }
            System.out.println("----------------------------------------");

            NodeList locationList = doc.getElementsByTagName("location");
            for (int i = 0; i < locationList.getLength(); i++) {
                Node p = locationList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element location = (Element) p;
                    System.out.println("..." + location.getTagName());
                    NodeList dataList = location.getChildNodes();
                    for (int j = 0; j < dataList.getLength(); j++) {
                        Node n = dataList.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element data = (Element) n;
                            if (data.getTagName() == "locationName") {
                                System.out.println("......" + data.getTagName() +" "+ data.getTextContent());
                            } else if (data.getTagName() == "weatherElement") {
//                                System.out.println("... going to parse weatherElement");
                                System.out.println("......" + data.getTagName());
                                NodeList weatherElementList = data.getChildNodes();
//                                System.out.println("... going to parse weatherElement, weatherElementList.getLength()=" + weatherElementList.getLength());
//                                System.out.println("... going to parse weatherElement, item 0 NodeName=" + weatherElementList.item(0).getNodeName());

                                for (int k = 0; k < weatherElementList.getLength(); k++) {
//                                    System.out.println("......k=" + k);
                                    Node nodeK = weatherElementList.item(k);
                                    if (nodeK.getNodeType() == Node.ELEMENT_NODE) {
                                        Element weather = (Element) nodeK;
                                        if (weather.getTagName() == "elementName") {
                                            System.out.println("........." + weather.getTagName() + " " + weather.getTextContent());

                                        } else if (weather.getTagName() == "time") {
                                            System.out.println("........." + weather.getTagName());

                                            NodeList timeList = weather.getChildNodes();

                                            for (int m = 0; m < timeList.getLength(); m++) {
//                                    System.out.println("......k=" + k);
                                                Node nodeM = timeList.item(m);
                                                if (nodeM.getNodeType() == Node.ELEMENT_NODE) {

                                                    Element time = (Element) nodeM;
                                                    if (time.getTagName() == "startTime") {
                                                        System.out.println("........." + time.getTagName() + " " + time.getTextContent());

                                                    } else if (time.getTagName() == "endTime") {
                                                        System.out.println("............" + time.getTagName() + " " + time.getTextContent());

                                                    } else if (time.getTagName() == "parameter") {
                                                        System.out.println("............parameter");

                                                        NodeList parameterList = time.getChildNodes();
                                                        for (int m2 = 0; m2 < parameterList.getLength(); m2++) {
//                                    System.out.println("......k=" + k);
                                                            Node nodeN = parameterList.item(m2);
                                                            if (nodeN.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element parameter = (Element) nodeN;
                                                                System.out.println("..............." + parameter.getTagName() + " " + parameter.getTextContent());
                                                            } else {
                                                                //    System.out.println("??? WHAT NOT ELEMENT_NODE");
                                                            }
                                                        }

                                                    } else {
                                                        System.out.println("??? NOT CONSIDERED" + time.getTagName() + " " + time.getTextContent());
                                                    }
                                                }

                                            }

                                        } else {
                                            System.out.println("...... WHAT ELSE?");

                                        }
                                    }
                                }

//                                for (int k = 0; j < weatherElementList.getLength(); j++) {
//                                    Node nodeK = weatherElementList.item(k);
//                                  //  if (nodeK.getNodeType() == Node.ELEMENT_NODE) {
//                                        Element weather = (Element) nodeK;
//                                        System.out.println("..." + weather.getTagName() + " " + weather.getTextContent());
//                                   // }
//
//                                }
                            } else {
//                                System.out.println("..." + data.getTagName() + " " + data.getTextContent());
                                System.out.println("...WHAT IS THIS?");
                            }
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
