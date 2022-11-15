package golan.izik;

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class TestGremlin {
    public static void main(String[] args) throws FileNotFoundException, ExecutionException, InterruptedException {

        Cluster cluster = null;
        try {
            cluster = Cluster.build(new File(getPathToConfigFile())).create();
            final Client client = cluster.connect();
            final ResultSet a_12 = client.submit("g.addV('tracker').property('id', 'A_12').property('env_uuid', 'AAAAAAAA-AAAA-AAAA-AAAA-000000000000')");
            a_12.all().get();
//            final ResultSet julia = client.submit("g.addV('author').property('name','Julia Child').property('gender','F')");
//            final ResultSet result = client.submit("g.V()");
//            final Iterator<Result> it = result.iterator();
//            while (it.hasNext()) {
//                Result next = it.next();
//                System.out.println(next);
//            }
        } finally {
            if (cluster != null)
            cluster.close();
        }


    }

    private static String getPathToConfigFile() {
        return System.getenv().getOrDefault("COSMOS_CONFIG_FILE", "src/main/resources/remote.yaml");
    }


}
