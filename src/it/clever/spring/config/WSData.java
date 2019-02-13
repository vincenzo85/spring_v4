package it.clever.spring.config;
//@Component ... la sto annotando per esempio...
//@Scope ("singleton")
public class WSData {

	
	// @Autowired
	// private ConfigurationBean cfBean;
	
	// dovrei fare una cosa del tipo 
	// @PostCostruct 
	// public void init(){
	// qui devo fare una cosa del tipo 
	//   cfBean. te beccati le property.... 
	//	}
	// dovrei alimentare questo metodo con i parametri dei file di property... 
	// 
	
	
	private String webserviceUrl;

	public String getWebserviceUrl() {
		return webserviceUrl;
	}

	public void setWebserviceUrl(String webserviceUrl) {
		this.webserviceUrl = webserviceUrl;
	}
	
	
}
