import java.util.*;
import java.io.*;

public class MostActiveCookie {

    List<String> cookies;
    HashMap<String, List<String>> dateToCookieMap;

    public void readFile(String filePath) throws IOException
    {
        this.cookies = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
            String row;
            csvReader.readLine(); // Skip titles of file
            while ((row = csvReader.readLine()) != null)
            {
                String[] data = row.split(",");
                this.cookies.add(data[0].replace("\"", "") + " " + data[1].replace("\"", "")); // Remove commas added by CSV file
            }
            csvReader.close();
        }
        catch(IOException x)
        {
            System.out.println("file not found");
            System.exit(0);
        }
    }

    public void getMostActiveCookies(String date)
    {
        List<String> activeCookies;
        mapCookies(); //Call helper to map dates to cookies

        if(!this.dateToCookieMap.containsKey(date))
        {
            System.out.println("Invalid date or date not found");
            return;
        }
        else
        {
            activeCookies = this.dateToCookieMap.get(date);
        }

        Map<String, Integer> curr = new HashMap<>();
        PriorityQueue<String[]> maxHeap = new PriorityQueue<>((a,b) -> b[1].compareTo(a[1]));

        for(String cookie : activeCookies)
        {
            curr.put(cookie, curr.getOrDefault(cookie, 0) + 1);
        }

        // Find most active cookie
        int mostActiveCookieLogs = -1;

        for(Map.Entry<String, Integer> entry : curr.entrySet())
        {
            mostActiveCookieLogs = Math.max(mostActiveCookieLogs, entry.getValue());
        }

        for(Map.Entry<String, Integer> entry : curr.entrySet())
        {
            // If multiple cookies have the same number of logs add to maxHeap to sort them based on time
            if(entry.getValue() == mostActiveCookieLogs)
            {
                String key = entry.getKey();
                maxHeap.add(new String[] { key.substring(0, key.indexOf(" ")), key.substring(key.indexOf(" ") + 1)});
            }
        }

        // Output based on most recent timestamp
        while(!maxHeap.isEmpty()) System.out.println(maxHeap.poll()[0]);
    }

    private void mapCookies()
    {
        // Map date to all the cookies on given day
        HashMap<String, List<String>> map = new HashMap<>();

        for(String cookie : this.cookies)
        {
            String cook = cookie.substring(0, cookie.indexOf(" "));
            String date = cookie.substring(cookie.indexOf(" ") + 1, cookie.indexOf("T"));
            String time = cookie.substring(cookie.indexOf("T") + 1);

            map.computeIfAbsent(date, l -> new ArrayList<String>()).add(cook + " " + time);
        }

        this.dateToCookieMap = map;
    }
}
