package com.withdog.web.dogbreeddic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageSource;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.DogBreedDic;

@RestController
@RequestMapping("/dogBreedDic/*")
public class DogBreedDicRestController {
	
	@Autowired
	@Qualifier("dogBreedDicServiceImpl")
	private DogBreedDicService dogBreedDicService;
	
	public DogBreedDicRestController(){
		System.out.println(this.getClass());
		
	}
	@RequestMapping(value = "json/getDogBreed")
	public JSONObject getDogBreed(@RequestBody DogBreedDic dogBreedDic,HttpSession session) throws Exception{
		
		System.out.println("/dogBreedDic/json/getDogBreed");
		System.out.println("확인중"+dogBreedDic);
		DogBreedDic dogBreed = dogBreedDicService.getDogBreed(dogBreedDic.getDogBreedEN());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", dogBreed);
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getDogBreed2") //소현누나꺼
	public JSONObject getDogBreed2(@RequestBody DogBreedDic dogBreedDic  ) throws Exception{
		
		System.out.println("/dogBreedDic/json/getDogBreed");
		System.out.println("확인중"+dogBreedDic);
		DogBreedDic dogBreed = dogBreedDicService.getDogBreedInfo(dogBreedDic.getDogNo());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", dogBreed);
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getDogBreedInfoList")
	public JSONObject getDogBreedInfoList(@RequestBody DogBreedDic dogBreedDic) throws Exception{
		System.out.println("/dogBreedDic/json/getDogBreedInfo");
		List<DogBreedDic> dogBreedInfo = dogBreedDicService.getDogBreedInfoList(dogBreedDic.getDogBreedKO());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dogBreedInfo", dogBreedInfo);
		System.out.println(jsonObject.toJSONString());
		return jsonObject;
	}
	
	///////////////////
	
	@RequestMapping(value = "json/getDogBreedInfoListAndroid")
	public JSONObject getDogBreedInfoListAndroid(HttpServletRequest req) throws Exception{
		System.out.println("/dogBreedDic/json/getDogBreedInfo");
		System.out.println(req.getParameter("dogBreedKO"));
		
		List<DogBreedDic> dogBreedInfo = dogBreedDicService.getDogBreedInfoList(req.getParameter("dogBreedKO"));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dogBreedInfo", dogBreedInfo);
		System.out.println(jsonObject.toJSONString());
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getAllDogBreedNameListByKo")
	public JSONObject getAllDogBreedNameListByKo() throws Exception{
		System.out.println("/dogBreedDic/json/getAllDogBreedNameListByKo");
		List<DogBreedDic> allDogBreedName = dogBreedDicService.getAllDogBreedNameListByKo();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDogBreedName", allDogBreedName);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getUriVisionAndroid")
	public JSONObject getUriVisionAndroid(HttpServletRequest req) throws Exception{
		System.out.println("/dogBreedDic/json/getUriVisionAndroid");
		PrintStream ps = System.out;
		
		JSONObject jsonObject = new JSONObject();
		if(req.getParameter("uri") != "") {
			jsonObject.put("uri", detectLabelsGcs(req.getParameter("uri"), ps));
		}else {
			jsonObject.put("uri", "http://192.168.0.46:8080/images/noresult_image.png");
		}
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	/////////////////////
	
	@RequestMapping(value = "json/getAllBreedInfoList")
	public JSONObject getAllDogBreedInfoList() throws Exception{
		System.out.println("/dogBreedDic/json/getAllDogBreedInfo");
		List<DogBreedDic> allDogBreedInfo = dogBreedDicService.getAllDogBreedInfoList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDogBreedInfo", allDogBreedInfo);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getAllBreedInfoListByKo")
	public JSONObject getAllDogBreedInfoListByKo() throws Exception{
		System.out.println("/dogBreedDic/json/getAllDogBreedInfo");
		List<DogBreedDic> allDogBreedInfo = dogBreedDicService.getAllDogBreedInfoListByKo();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDogBreedInfo", allDogBreedInfo);
		return jsonObject;
	}
	
	public static String detectLabelsGcs(String gcsPath, PrintStream out) throws Exception, IOException {
		List<AnnotateImageRequest> requests = new ArrayList<>();

		ImageSource imgSource = ImageSource.newBuilder().setImageUri(gcsPath).build();
		Image img = Image.newBuilder().setSource(imgSource).build();
		Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);

		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

//			for (AnnotateImageResponse res : responses) {
//				if (res.hasError()) {
//					out.printf("Error: %s\n", res.getError().getMessage());
//					return;
//				}
//	        	
//				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
//		        annotation.getAllFields().forEach((k, v) ->
//		        	out.printf("%s : %s\n", k, v.toString()));
//		      	}
//			}
			System.out.println("개가 들어있나? "+responses.toString().indexOf("dog"));
			if(responses.toString().indexOf("dog") != -1) {
				return gcsPath;
			}else {
				return "http://192.168.0.46:8080/images/noresult_image.png";
			}
		}
	}
}
