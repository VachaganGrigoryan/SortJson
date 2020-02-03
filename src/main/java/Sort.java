
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sort {

    private static void writeUsingFileWriter(String file_name, String data) {
        File file = new File(file_name);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String SortByPython(String json){
        String s = null;
        writeUsingFileWriter("{path}/json_file.json", json);
        try {
            Process p = Runtime.getRuntime().exec("python2 sort.py");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Successfully completed the JSON Sorting By Python Function:\n");
            while ((s = stdInput.readLine()) != null) {
                return s;
            }

            // read any errors from the attempted command
            System.out.println("Error of the command [JSON Sorting By Python Function]:\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Exception happened in the JSON Sorting By Python Function - here's what I know: \n");
            e.printStackTrace();
            System.exit(-1);
        }
        return s;
    }

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("/home/vachagan/IdeaProjects/SortJson/src/main/java/example.json"));

        String json = jsonObject.toJSONString();

        String sorted_json = SortByPython(json);

        System.out.println(sorted_json);
//
//        String replacedJson = sorted_json.replace('\'','\"');
//        System.out.println(replacedJson);
//
//
//        JSONObject jobj = (JSONObject) parser.parse(replacedJson);
//
//        writeUsingFileWriter("sorted.json", jobj.toJSONString());

    }
}
