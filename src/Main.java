import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 3)
        {
            System.out.println("Invalid input");
            System.exit(0);
        }

        String file = args[0];
        String date = args[2];

        MostActiveCookie mac = new MostActiveCookie();
        mac.readFile("/Users/amitbhandal/Downloads/mostCookies/data/" + file);
        mac.getMostActiveCookies(date);
    }
}
