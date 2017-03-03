package motech.nms.test;

import junit.framework.Assert;
import motech.nms.Constants.MotechUpdaterConstants;
import motech.nms.Csv.CsvModel.CsvModelFlw;
import motech.nms.Csv.CsvReaderFlw;
import motech.nms.FlwRequest.AddFlwRequest;
import motech.nms.FlwRequest.AddFlwRequestBuilder;
import motech.nms.HttpMethods.FlwRequestHttpMethods;
import motech.nms.util.Util;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MotechFlwTests {

    private static final String SERVER_IP_PORT = "localhost:8080"; // "192.168.200.111:8080";

    @Test public void csvReaderTest() throws Exception {
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flws = csvReaderFlw
                .read("src/motech/nms/test/resources/test.csv");
        Assert.assertEquals(flws.get(0).toString(),
                "CsvModelFlw{name='Loren1', mctsFlwId='1111111111', contactNumber='9856423170', stateCode='2', districtCode='21', talukaCode='23', phcCode='27', subcentreCode='23', villageCode='22', healthblockCode='33', type='TYPE', gfStatus='Active'}");
        Assert.assertEquals(flws.get(1).toString(),
                "CsvModelFlw{name='Loren2', mctsFlwId='1111111111', contactNumber='9865321470', stateCode='17', districtCode='21', talukaCode='29', phcCode='31', subcentreCode='37', villageCode='42', healthblockCode='39', type='TYPE' gfStatus='Inactive'}");
    }

    @Test public void builderTest() throws Exception {
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flws = csvReaderFlw
                .read("src/motech/nms/test/resources/test.csv");
        AddFlwRequest flwRequest = AddFlwRequestBuilder
                .build(flws.get(0).getName(), flws.get(0).getMctsFlwId(),
                        flws.get(0).getContactNumber(),
                        flws.get(0).getStateCode(),
                        flws.get(0).getDistrictCode(),
                        flws.get(0).getTalukaCode(), flws.get(0).getPhcCode(),
                        flws.get(0).getSubcentreCode(),
                        flws.get(0).getVillageCode(),
                        flws.get(0).getHealthblockCode(),
                        flws.get(0).getType(),
                        flws.get(0).getGfStatus());
        Assert.assertEquals(flwRequest.toString(),
                "AddFlwRequest{name='Loren1', mctsFlwId='1111111111', contactNumber=9856423170, stateId=10, districtId=21, talukaId='23', phcId=27, subcentreId=23, villageId=22, healthblockId=33, type='TYPE'}");
    }

    @Test public void GsonBuilderTest() throws Exception {
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flws = csvReaderFlw
                .read("src/motech/nms/test/resources/test.csv");
        AddFlwRequest flwRequest = AddFlwRequestBuilder
                .build(flws.get(0).getName(), flws.get(0).getMctsFlwId(),
                        flws.get(0).getContactNumber(),
                        flws.get(0).getStateCode(),
                        flws.get(0).getDistrictCode(),
                        flws.get(0).getTalukaCode(), flws.get(0).getPhcCode(),
                        flws.get(0).getSubcentreCode(),
                        flws.get(0).getVillageCode(),
                        flws.get(0).getHealthblockCode(),
                        flws.get(0).getType(),
                        flws.get(0).getGfStatus());
        System.out.println( new FlwRequestHttpMethods().converttoJson(flwRequest));
        Assert.assertEquals(
                new FlwRequestHttpMethods().converttoJson(flwRequest),
                "{\"name\":\"Loren1\",\"mctsFlwId\":\"1111111111\",\"contactNumber\":9856423170,\"stateId\":10,\"districtId\":21,\"talukaId\":\"23\",\"phcId\":27,\"subcentreId\":23,\"villageId\":22,\"healthblockId\":33,\"type\":\"TYPE\"}");
    }

    @Test public void PostRequestTests() throws Exception {
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flws = csvReaderFlw
                .read("src/motech/nms/test/resources/test.csv");

        AddFlwRequest flwRequest = AddFlwRequestBuilder
                .build(flws.get(0).getName(), flws.get(0).getMctsFlwId(),
                        flws.get(0).getContactNumber(),
                        flws.get(0).getStateCode(),
                        flws.get(0).getDistrictCode(),
                        flws.get(0).getTalukaCode(), flws.get(0).getPhcCode(),
                        flws.get(0).getSubcentreCode(),
                        flws.get(0).getVillageCode(),
                        flws.get(0).getHealthblockCode(),
                        flws.get(0).getType(),
                        flws.get(0).getGfStatus());
        FlwRequestHttpMethods flwRequestHttpMethods = new FlwRequestHttpMethods();
        String header_authorization = flwRequestHttpMethods.createAuthenticationHeader(
                MotechUpdaterConstants.DEFAULT_USER,
                MotechUpdaterConstants.DEFAULT_PASSWORD);
        HttpPost post = flwRequestHttpMethods
                .createPostRequestWithJson(MotechUpdaterConstants.MOTECH_URL,
                        flwRequest,
                        header_authorization);
        System.out.println(header_authorization);
        Assert.assertEquals(post.getRequestLine().toString(),
                "POST http://"+SERVER_IP_PORT+"/motech-platform-server/module/api/ops/createUpdateFlw HTTP/1.1");
        Assert.assertEquals(post.getEntity().getContentType().getValue(),
                "application/json; charset=UTF-8");

        Assert.assertEquals(post.getHeaders(HttpHeaders.CONTENT_TYPE)[0].getValue(), ContentType.APPLICATION_JSON.getMimeType());
        Assert.assertEquals(
                post.getHeaders(HttpHeaders.AUTHORIZATION)[0].getValue(),
                header_authorization);
        Assert.assertEquals(
                Util.convertIOToString(post.getEntity().getContent()),
                "{\"name\":\"Loren1\",\"mctsFlwId\":\"1111111111\",\"contactNumber\":9856423170,\"stateId\":10,\"districtId\":21,\"talukaId\":\"23\",\"phcId\":27,\"subcentreId\":23,\"villageId\":22,\"healthblockId\":33,\"type\":\"TYPE\"}");

    }

    @Test public void PostResponseTests() throws IOException{
        CsvReaderFlw csvReaderFlw = new CsvReaderFlw();
        List<CsvModelFlw> flws = csvReaderFlw
                .read("src/motech/nms/test/resources/test.csv");
        AddFlwRequest flwRequest = AddFlwRequestBuilder
                .build(flws.get(0).getName(), flws.get(0).getMctsFlwId(),
                        flws.get(0).getContactNumber(),
                        flws.get(0).getStateCode(),
                        flws.get(0).getDistrictCode(),
                        flws.get(0).getTalukaCode(), flws.get(0).getPhcCode(),
                        flws.get(0).getSubcentreCode(),
                        flws.get(0).getVillageCode(),
                        flws.get(0).getHealthblockCode(),
                        flws.get(0).getType(),
                        flws.get(0).getGfStatus());
        FlwRequestHttpMethods flwRequestHttpMethods = new FlwRequestHttpMethods();
        int responseCode  = flwRequestHttpMethods.postwithJson(MotechUpdaterConstants.MOTECH_URL,flwRequest, flwRequestHttpMethods.createAuthenticationHeader(
                MotechUpdaterConstants.DEFAULT_USER, MotechUpdaterConstants.DEFAULT_PASSWORD
        ));
        Assert.assertEquals(responseCode, 200);
        System.out.println(responseCode);

    }


}
