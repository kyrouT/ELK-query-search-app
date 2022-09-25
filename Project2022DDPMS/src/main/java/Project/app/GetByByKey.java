package Project.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import java.io.IOException;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;





@RestController
public class GetByKey  {

	private ArrayList<String> result = new ArrayList<String>(); 
	
	@CrossOrigin
	@PostMapping("/get")
	public ArrayList<String> index(@RequestBody Request request, String indexValue )throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, NoSuchProviderException {
		
		ESManager EsManager = new ESManager();
        RestHighLevelClient HighClient = EsManager.CreateHighLevelClient();
		RestClient LowLevelClient = EsManager.CreateLowLevelClient(HighClient);
        InfoService infoService = new InfoService(HighClient);
		IndexService indexService = new IndexService(HighClient);
        result = indexService.ViewIndexOurs(request.indexName, request.indexValue, request.logNumber);
		return result;
	}

}