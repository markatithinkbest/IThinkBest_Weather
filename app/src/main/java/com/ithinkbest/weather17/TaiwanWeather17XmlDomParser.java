package com.ithinkbest.weather17;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author MARK
 */
public class TaiwanWeather17XmlDomParser {
    public static final String LOG_TAG = "MARK987";
    private static final String URL2 =
            "http://opendata.cwb.gov.tw/opendataapi?dataid=F-C0032-005&authorizationkey=CWB-B2CA193D-2DE6-4121-AF6A-BFA9C12713BF";
    static int weatherElemenetCode = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static List<WeatherEntry> parse() {
        List<WeatherEntry> weatherEntryList = new ArrayList<WeatherEntry>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Document can easily handle source from local file or net
//            Document doc = builder.parse("C:\\xmlfiles\\F-C0032-005.xml");
            Document doc = builder.parse(URL2);

//            Log.d(LOG_TAG, "...DOING PARSE...");

            // paring single datasetInfo
            NodeList dataInfoList = doc.getElementsByTagName("datasetInfo");
            for (int x = 0; x < dataInfoList.getLength(); x++) {
                Node dataInfoNode = dataInfoList.item(x);// only one
                if (dataInfoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element e1 = (Element) dataInfoNode;
//                    Log.d(LOG_TAG, "..." + e1.getTagName());
                    NodeList e2List = e1.getChildNodes();
                    for (int x2 = 0; x2 < e2List.getLength(); x2++) {
                        Node e2Node = e2List.item(x2);
                        if (e2Node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e2 = (Element) e2Node;
//                            Log.d(LOG_TAG, "......" + e2.getTagName() + " " + e2.getTextContent());
                        }
                    }
                }

            }
//            Log.d(LOG_TAG, "----------------------------------------");
            NodeList locationList = doc.getElementsByTagName("location");

            WeatherEntry weatherEntry = null;


            for (int i = 0; i < locationList.getLength(); i++) {
                Node p = locationList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element location = (Element) p;
//                    Log.d(LOG_TAG, "---" + location.getTagName().toString());

                    NodeList dataList = location.getChildNodes();
                    for (int j = 0; j < dataList.getLength(); j++) {
                        Node n = dataList.item(j);

                        if (n.getNodeType() == Node.ELEMENT_NODE) {
                            Element data = (Element) n;


                            if (data.getTagName().equals("locationName")) {
//                                Log.d(LOG_TAG, "------" + data.getTagName().toString() + " " + data.getTextContent().toString());

                                weatherEntry = new WeatherEntry();
                                weatherEntry.setLocationName(data.getTextContent().toString());

                            } else if (data.getTagName().equals("weatherElement")) {
//                                Log.d(LOG_TAG, "------" + data.getTagName());
                                NodeList weatherElementList = data.getChildNodes();

                                // so it will be used starting from 0
                                int timeCode = -1;

                                for (int k = 0; k < weatherElementList.getLength(); k++) {
// Log.d(LOG_TAG,"......k=" + k);
                                    Node nodeK = weatherElementList.item(k);
                                    //   Log.d(LOG_TAG,"......???k="+k+" " + ((Element)nodeK).getTagName().toString() + " " + ((Element)nodeK).getTextContent().toString());


                                    if (nodeK.getNodeType() == Node.ELEMENT_NODE) {

                                        Element weather = (Element) nodeK;

                                        if (weather.getTagName().equals("elementName")) {
//                                            Log.d(LOG_TAG, "---------" + weather.getTagName() + " " + weather.getTextContent());
                                            if (weather.getTextContent().equals("Wx")) {
                                                weatherElemenetCode = 1;
                                            }
                                            if (weather.getTextContent().equals("MaxT")) {
                                                weatherElemenetCode = 2;

                                            }
                                            if (weather.getTextContent().equals("MinT")) {
                                                weatherElemenetCode = 3;

                                            }


                                        } else if (weather.getTagName().equals("time")) {
                                            timeCode++;

                                            //
                                            // <time> might be 14 or 15



                                            //

//                                            Log.d(LOG_TAG, "---------" + weather.getTagName());
                                            NodeList timeList = weather.getChildNodes();
                                            for (int m = 0; m < timeList.getLength(); m++) {
                                                Node nodeM = timeList.item(m);
                                                if (nodeM.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element time = (Element) nodeM;
                                                    if (time.getTagName().equals("startTime")) {
//                                                        Log.d(LOG_TAG, "------------" + time.getTagName() + " " + time.getTextContent());
//                                                        Log.d(LOG_TAG, "------------timeCode=" + timeCode);
                                                        if (weatherElemenetCode == 1) {
                                                            weatherEntry.setStartTime(time.getTextContent(), timeCode);
                                                        }


                                                    } else if (time.getTagName().equals("endTime")) {
//                                                        Log.d(LOG_TAG, "------------" + time.getTagName() + " " + time.getTextContent());
                                                        if (weatherElemenetCode == 1) {
                                                            weatherEntry.setEndTime(time.getTextContent(), timeCode);
                                                        }

                                                    } else if (time.getTagName().equals("parameter")) {
//                                                        Log.d(LOG_TAG, "------------parameter");
                                                        NodeList parameterList = time.getChildNodes();
                                                        for (int m2 = 0; m2 < parameterList.getLength(); m2++) {
                                                            Node nodeN = parameterList.item(m2);
                                                            if (nodeN.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element parameter = (Element) nodeN;
//                                                                Log.d(LOG_TAG, "---------------" + parameter.getTagName() + " " + parameter.getTextContent());
                                                                if (parameter.getTagName().equals("parameterName")) {

                                                                    switch (weatherElemenetCode) {
                                                                        case 1:
                                                                            weatherEntry.setParameterWxName(parameter.getTextContent(), timeCode);
                                                                            break;
                                                                        case 2:
                                                                            weatherEntry.setParameterMaxTName(Integer.parseInt(parameter.getTextContent()), timeCode);
                                                                            break;
                                                                        case 3:
                                                                            weatherEntry.setParameterMinTName(Integer.parseInt(parameter.getTextContent()), timeCode);
                                                                            break;

                                                                    }

                                                                }

                                                                if (weatherElemenetCode == 1 && parameter.getTagName().equals("parameterValue")) {

                                                                    weatherEntry.setParameterWxValue(Integer.parseInt(parameter.getTextContent()), timeCode);
                                                                }
                                                                //
                                                                if (weatherElemenetCode == 3 && timeCode == 13 && parameter.getTagName().equals("parameterUnit")) {
                                                                    weatherEntryList.add(weatherEntry);
//                                                                    Log.d(LOG_TAG, "###### adding---------------");

                                                                }
                                                            } else {
                                                                // Log.d(LOG_TAG,"??? WHAT NOT ELEMENT_NODE");
                                                            }
                                                        }
                                                    } else {
//                                                        Log.d(LOG_TAG, "??? NOT CONSIDERED" + time.getTagName() + " " + time.getTextContent());
                                                    }
                                                }
                                            }
                                        } else {
//                                            Log.d(LOG_TAG, "...... WHAT ELSE?");
                                        }
                                    }
                                }
                            } else {
//                                Log.d(LOG_TAG, "...WHAT IS THIS? i=" + i + "  type is " + n.getNodeType());


                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(LOG_TAG,"$$$$$$$$$$$$$$$$$$$$$$$$$$$$ EXCEPTION HERE?"+e.toString());
        }

//        Log.d(LOG_TAG,weatherEntryList.size()+" entries!!!");
//        for (int i=0;i<weatherEntryList.size();i++){
//            Log.d(LOG_TAG,weatherEntryList.get(i).toString());
//        }
        return weatherEntryList;
    }

}
