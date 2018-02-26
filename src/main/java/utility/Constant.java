package utility;


public class Constant {

		public static final String PATH_TO_CHROME_DRIVER = "/driver/chromedriver";
		public static final String PATH_TO_IE_DRIVER = "/driver/IEDriverServer_x64";
		public static final String PATH_TO_OPERA_DRIVER = "/driver/operadriver";
		public static final String PATH_TO_SAFARI_DRIVER = "/driver/chromedriver";
		public static final String DIR_TO_CONFIG_FILE = "/src/test/"; 
		public static final String DIR_TO_API_SCHEMA_FILE = "/src/test/resources/api/";
		public static final String USRDIR=System.getProperty("user.dir");
		
		
		public static final int EXPLICITWAIT_MILLIS = 3000;
		public static final int IMPLICITWAIT_POLL_MILLIS = 300;
		public static final int IMPLICITWAIT_MILLIS = 30000;
		public static final int WAIT_AJAXELEMENT_MILLIS = 4000;
		public static final int HIGHLIGHT_ELEMENT_MILLIS = 2000;
		public static final boolean PRINT_ELEMENT_NOT_FOUND_MSG = false;
		public static final boolean ENABLE_HIGHLIGHTER = false;
		public static final boolean DEBUG_MODE = true;
		public static boolean ENABLE_BROWSER_MOB = false;
		public static boolean ENABLE_FUNNEL_TESTER = false;
		public static boolean ENABLE_USER_INPUT = true;
		
		public static final String DATE_FORMAT = "MM-dd-yyyy";
		public static WebDriverUtils.Type DEFAULT_BROWSER = WebDriverUtils.Type.Chrome;
}
