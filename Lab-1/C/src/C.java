import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class C {
    public static void main(String[] args) throws IOException {
        String beginning = "$session = New-Object Microsoft.PowerShell.Commands.WebRequestSession\n" +
                "$session.UserAgent = \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36\"\n" +
                "$session.Cookies.Add((New-Object System.Net.Cookie(\"JSESSIONID\", \"2FA9BFED38D2E12C5D3B1948B58B76E2\", \"/\", \"1d3p.wp.codeforces.com\")))\n" +
                "Invoke-WebRequest -UseBasicParsing -Uri \"http://1d3p.wp.codeforces.com/new\" `\n" +
                "-Method \"POST\" `\n" +
                "-WebSession $session `\n" +
                "-Headers @{\n" +
                "\"Accept\"=\"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\"\n" +
                "  \"Accept-Encoding\"=\"gzip, deflate\"\n" +
                "  \"Accept-Language\"=\"ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\"\n" +
                "  \"Cache-Control\"=\"max-age=0\"\n" +
                "  \"Origin\"=\"http://1d3p.wp.codeforces.com\"\n" +
                "  \"Referer\"=\"http://1d3p.wp.codeforces.com/\"\n" +
                "  \"Upgrade-Insecure-Requests\"=\"1\"\n" +
                "} `\n" +
                "-ContentType \"application/x-www-form-urlencoded\" `\n" +
                "-Body \"_af=34be50b38beccce4&";
        String end = "&submit=Submit\"";
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ropka\\OneDrive\\Рабочий стол\\web\\test.ps1"
        ));


        for (int i = 1; i < 101; i++) {

            String tempStr = "proof=" + Integer.toString(i * i) + "&amount=" + Integer.toString(i);
            writer.write(beginning +
                    tempStr + end);
            writer.newLine();
        }

        writer.close();
    }
}
