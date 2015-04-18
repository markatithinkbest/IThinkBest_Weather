package com.example.android.networkusage;

import android.util.Log;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
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

    public static void parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Document can easily handle source from local file or net
//            Document doc = builder.parse("C:\\xmlfiles\\F-C0032-005.xml");
            Document doc = builder.parse(URL2);

            Log.d(LOG_TAG, "...DOING PARSE...");

            // paring single datasetInfo
            NodeList dataInfoList = doc.getElementsByTagName("datasetInfo");
            for (int x = 0; x < dataInfoList.getLength(); x++) {
                Node dataInfoNode = dataInfoList.item(x);// only one
                if (dataInfoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e1 = (Element) dataInfoNode;
//                    Log.d(LOG_TAG,"..." + e1.getTagName() + " " + e1.getTextContent());
                    Log.d(LOG_TAG,"..." + e1.getTagName());
                    NodeList e2List = e1.getChildNodes();
                    for (int x2 = 0; x2 < e2List.getLength(); x2++) {
                        Node e2Node = e2List.item(x2);
                        if (e2Node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e2 = (Element) e2Node;
                            Log.d(LOG_TAG,"......" + e2.getTagName() + " " + e2.getTextContent());
                        }
                    }
                }

            }
            Log.d(LOG_TAG,"----------------------------------------");
            Log.d(LOG_TAG, "...DOING PARSE...");
            NodeList locationList = doc.getElementsByTagName("location");
            Log.d(LOG_TAG, "...locationList CNT "+locationList.getLength());

            for (int i = 0; i < locationList.getLength(); i++) {
                Node p = locationList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element location = (Element) p;
                    Log.d(LOG_TAG,"...i="+ i+" "+ location.getTagName().toString());
                    NodeList dataList = location.getChildNodes();
                    for (int j = 0; j < dataList.getLength(); j++) {
                        Node n = dataList.item(j);

                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element data = (Element) n;
                           Log.d(LOG_TAG,"......???j="+j+" " + data.getTagName().toString() + " " + data.getTextContent().toString());


                            if (data.getTagName() == "locationName") {
                                Log.d(LOG_TAG,"......" + data.getTagName().toString() + " " + data.getTextContent().toString());
                            } else if (data.getTagName() == "weatherElement") {
// Log.d(LOG_TAG,"... going to parse weatherElement");
                                Log.d(LOG_TAG,"......" + data.getTagName());
                                NodeList weatherElementList = data.getChildNodes();
// Log.d(LOG_TAG,"... going to parse weatherElement, weatherElementList.getLength()=" + weatherElementList.getLength());
// Log.d(LOG_TAG,"... going to parse weatherElement, item 0 NodeName=" + weatherElementList.item(0).getNodeName());
                                for (int k = 0; k < weatherElementList.getLength(); k++) {
// Log.d(LOG_TAG,"......k=" + k);
                                    Node nodeK = weatherElementList.item(k);
                                    Log.d(LOG_TAG,"......???k="+k+" " + ((Element)nodeK).getTagName().toString() + " " + ((Element)nodeK).getTextContent().toString());


                                    if (nodeK.getNodeType() == Node.ELEMENT_NODE) {

                                        Element weather = (Element) nodeK;
                                        Log.d(LOG_TAG,"......k="+k+" " + weather.getTagName().toString() + " " + weather.getTextContent().toString());

                                        if (weather.getTagName() == "elementName") {
                                            Log.d(LOG_TAG,"........." + weather.getTagName() + " " + weather.getTextContent());
                                        } else if (weather.getTagName() == "time") {
                                            Log.d(LOG_TAG,"........." + weather.getTagName());
                                            NodeList timeList = weather.getChildNodes();
                                            for (int m = 0; m < timeList.getLength(); m++) {
// Log.d(LOG_TAG,"......k=" + k);
                                                Node nodeM = timeList.item(m);
                                                if (nodeM.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element time = (Element) nodeM;
                                                    if (time.getTagName() == "startTime") {
                                                        Log.d(LOG_TAG,"........." + time.getTagName() + " " + time.getTextContent());
                                                    } else if (time.getTagName() == "endTime") {
                                                        Log.d(LOG_TAG,"............" + time.getTagName() + " " + time.getTextContent());
                                                    } else if (time.getTagName() == "parameter") {
                                                        Log.d(LOG_TAG,"............parameter");
                                                        NodeList parameterList = time.getChildNodes();
                                                        for (int m2 = 0; m2 < parameterList.getLength(); m2++) {
// Log.d(LOG_TAG,"......k=" + k);
                                                            Node nodeN = parameterList.item(m2);
                                                            if (nodeN.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element parameter = (Element) nodeN;
                                                                Log.d(LOG_TAG,"..............." + parameter.getTagName() + " " + parameter.getTextContent());
                                                            } else {
// Log.d(LOG_TAG,"??? WHAT NOT ELEMENT_NODE");
                                                            }
                                                        }
                                                    } else {
                                                        Log.d(LOG_TAG,"??? NOT CONSIDERED" + time.getTagName() + " " + time.getTextContent());
                                                    }
                                                }
                                            }
                                        } else {
                                            Log.d(LOG_TAG,"...... WHAT ELSE?");
                                        }
                                    }
                                }
                            } else {
                                Log.d(LOG_TAG,"...WHAT IS THIS? i="+i+"  type is "+n.getNodeType());


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
