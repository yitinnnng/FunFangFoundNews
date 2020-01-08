import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject2.jsp")
public class TestProject2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestProject2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search2.jsp").forward(request, response);
			return;
		}

		EnglishQuery google = new EnglishQuery(request.getParameter("keyword"));

		HashMap<String, String> query = google.query();
		HashMap<String, String> time = google.title_time;
		if (query.isEmpty()) {
			request.getRequestDispatcher("NoResults.jsp")
			 .forward(request, response); 
			return;
		}
		String[][] s = new String[query.size()][4];
		Date date = new Date();
	    SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");       
	    String bbb = a.format(date);
		WebPage rootPage = new WebPage(google.url, "Google Search Results", bbb);

		WebTree tree = new WebTree(rootPage);
		request.setAttribute("query", s);

		int childNum = 0;
		for (Entry<String, String> entry : query.entrySet()) {
			String title = entry.getKey();
			String url = entry.getValue();
			for (Entry<String, String> tt : time.entrySet()) {
				String title2 = tt.getKey();
				String times = tt.getValue();
				if (title == title2) {
					tree.root.addChild(new WebNode(new WebPage(url, title, times)));
					ChildQuery childweb = new ChildQuery(url);

					childweb.getChildWeb();
					tree.root.children.get(childNum).setWebType(childweb.getNewsType());
					if (!childweb.childurl1.equals("noChild")) {

						tree.root.children.get(childNum)
								.addChild(new WebNode(new WebPage(childweb.childurl1, "kid1", times)));
//	        System.out.println(childweb.childurl1);
						tree.root.children.get(childNum)
								.addChild(new WebNode(new WebPage(childweb.childurl2, "kid2", times)));
//	        System.out.println(childweb.childurl2);

					}
					childNum++;

				}

			}

		}

		ArrayList<Keyword> keywordList = new ArrayList<Keyword>();

		if (request.getParameter("keyword").contains(" ")) {
			String keywords[] = request.getParameter("keyword").split(" ");

			for (String keyword : keywords) {
				keywordList.add(new Keyword(keyword, 3));
				try {
					tree.setPostOrderScore(keywordList);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			keywordList.add(new Keyword(request.getParameter("keyword"), 3));
			try {
				tree.setPostOrderScore(keywordList);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		tree.setTreeOrder();

		int num = 0;
		for (WebNode node : tree.root.children) {
			String webName = node.webPage.name;
			String webUrl = node.webPage.url;
			String webType = node.webType;
			String webTime = node.webPage.time;
			s[num][0] = webName;// add to the array
			s[num][1] = webUrl;
			s[num][2] = webType+"     ";
			s[num][3] = webTime;
			num++;
		}

		tree.eularPrintTree();

		request.getRequestDispatcher("SearchResults.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static String encodeURL(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Error: " + e.getMessage();
		}
	}

}