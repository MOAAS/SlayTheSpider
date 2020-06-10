package SlayTheSpider.Model.DocLoader;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.ClassLoader.getSystemClassLoader;

public class StringListLoader implements FileLoader<List<String>> {
    private final String pathname;

    public StringListLoader(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public List<String> load() {
        List<String> strings = new ArrayList<>();
        try {
            InputStream in = getSystemClassLoader().getResourceAsStream(pathname);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();
            while (line != null) {
                strings.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
