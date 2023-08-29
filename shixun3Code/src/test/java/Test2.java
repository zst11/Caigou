import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.baidu.com");
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
//        String str = "java是两块豆腐java12";
//        Pattern p = Pattern.compile("java\\d{0,2}");
//        Matcher m = p.matcher(str);
//        while (m.find()){
//            String s = m.group();
//            System.out.println(s);
//        }
    }
}
