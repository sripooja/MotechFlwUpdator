package motech.nms.Updator;

import motech.nms.Constants.MotechUpdaterConstants;
import motech.nms.Csv.CsvModel.CsvModelFlw;
import motech.nms.Csv.CsvReaderFlw;
import motech.nms.FlwRequest.AddFlwRequest;
import motech.nms.FlwRequest.AddFlwRequestBuilder;
import motech.nms.HttpMethods.FlwRequestHttpMethods;
import motech.nms.config.ConfigReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MotechUpdater {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Provide file Path as Argument.");
            return;
        }
        System.out.println("File Path provided is " + args[0]);
        ConfigReader configReader = new ConfigReader(args[0]);
        String motech_url = configReader.getProperty("MOTECH_URL");
        System.out.println(motech_url);
        String user = configReader.getProperty("DEFAULT_USER");
        String password = configReader.getProperty("DEFAULT_PASSWORD");
        String csv_path = configReader.getProperty("CSV_PATH_COMPLETE");
        System.out.println("csv_path" + csv_path);
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flwsComplete = csvReaderFlw.read(csv_path);
        ArrayList<CsvModelFlw> flws = new ArrayList();
        flws.addAll(flwsComplete);
        HashMap<Integer, Integer> statusCounter = new HashMap<Integer, Integer>();
        Date starttime = new Date(System.currentTimeMillis());
        System.out.println("Started at: " + starttime);
        int counter = 0;
        for (CsvModelFlw flw : flws) {
            AddFlwRequest flwRequest = AddFlwRequestBuilder.build((String)flw.getName(), (String)flw.getMctsFlwId(), (String)flw.getContactNumber(), (String)flw.getStateCode(), (String)flw.getDistrictCode(), (String)flw.getTalukaCode(), (String)flw.getPhcCode(), (String)flw.getSubcentreCode(), (String)flw.getVillageCode(), (String)flw.getHealthblockCode(), (String)flw.getType(), (String)flw.getGfStatus());
            FlwRequestHttpMethods flwRequestHttpMethods = new FlwRequestHttpMethods();
            if (flwRequest == null) continue;
            int resultCode = flwRequestHttpMethods.postwithJson(motech_url, (AddFlwRequest)flwRequest, flwRequestHttpMethods.createAuthenticationHeader(user, password));
            if (statusCounter.containsKey(resultCode)) {
                statusCounter.put(resultCode, statusCounter.get(resultCode) + 1);
            } else {
                statusCounter.put(resultCode, 1);
            }
            if (++counter % 1000 != 0) continue;
            MotechUpdater.printProgress(statusCounter);
        }
        MotechUpdater.printProgress(statusCounter);
        Date endtime = new Date(System.currentTimeMillis());
        System.out.println("Ended at: " + endtime);
    }

    private static void printProgress(Map<Integer, Integer> statusCounter) {
        Iterator<Integer> iterator = statusCounter.keySet().iterator();
        while (iterator.hasNext()) {
            int status = iterator.next();
            System.out.println(new Date(System.currentTimeMillis()));
            System.out.println("Status code: " + status + "     count: " + statusCounter.get(status) + "\n");
        }
    }
}