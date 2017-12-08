<%@ page import="com.saga.opencms.vue.SgSolrJson" %>
<%@ page import="org.apache.commons.io.IOUtils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.opencms.json.JSONException" %>
<%@ page import="org.opencms.json.JSONObject" %>
<%@ page import="org.opencms.main.CmsLog" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%@page buffer="none" session="false" trimDirectiveWhitespaces="true" %>

<%--LOAD DATA FROM PARAMETERS--%>
<%!

    final Log LOG = CmsLog.getLog("com.saga.opencms.vue.v-controller.jsp");

    public static final String PAYLOAD_PARAMS = "params";

    JSONObject params;

    private String locale;
    private String site;
    private String uri;
    private String index;

    private String query;
//    private String fields;
    private int iRows;
    private int iStart;

    private void load(HttpServletRequest request)
            throws IOException, JSONException {

        // get params from POST payload
        final String payLoad = IOUtils.toString(request.getReader());
        LOG.debug("payload: " + payLoad);
        params = new JSONObject(payLoad).getJSONObject(PAYLOAD_PARAMS);
        LOG.debug("params: " + params);

        // Rows
        iRows = 10;
        String rows = getParam("rows");
        if (StringUtils.isNotBlank(rows)) {
            try {
                iRows = Integer.valueOf(rows);
            } catch (Exception e) {
                LOG.error("rows parameter must be int " + rows, e);
            }
        }
        LOG.debug("rows: " + iRows);

        // Start
        iStart = 0;
        String start = getParam("start");
        if (StringUtils.isNotBlank(start)) {
            try {
                iStart = Integer.valueOf(start);
            } catch (Exception e) {
                LOG.error("start parameter must be int " + start, e);
            }
        }
        LOG.debug("start: " + iStart);

        // Query
        query = "";
        String queryParam = getParam("query");
        if (StringUtils.isNotBlank(queryParam)) {
            query = URLDecoder.decode(queryParam, "UTF-8");
        }
        LOG.debug("query: " + query);

        // fields
        /*String fieldsParam = getParam("fields");
        if (StringUtils.isNotBlank(fieldsParam)) {
            fields = fieldsParam;
        }*/

        locale = getParam("locale");
        LOG.debug("locale: " + locale);

        uri = getParam("uri");
        LOG.debug("uri: " + uri);

        site = getParam("site");
        LOG.debug("site: " + site);

        index = getParam("index");
        LOG.debug("index: " + index);
    }

    private String getParam(String paramName){
        String paramValue = null;
        try {
            paramValue = params.get(paramName).toString();
        } catch (JSONException e) {
            LOG.debug("Param " + paramName + " is not found in payload");
        }
        return paramValue;
    }

    private String generateQueryLocale() {
        String qLocale = "";
        if (StringUtils.isNotBlank(locale)) {
            qLocale = "&fq=con_locales:" + locale;
        }
        return qLocale;
    }

    private Map<String, String> loadCtxt() {
        Map<String, String> ctxt = new HashMap<String, String>();
        ctxt.put("locale", locale);
        ctxt.put("site", site);
        ctxt.put("uri", uri);
        ctxt.put("index", index);

        return ctxt;
    }

    private boolean validate() throws Exception {
        if (StringUtils.isBlank(locale)) {
            throw new Exception("locale must not be empty");
        }
        if (StringUtils.isBlank(query)) {
            throw new Exception("query must not be empty");
        }

        return true;
    }
%>
<%
    JSONObject jRes = new JSONObject();
    try {
        load(request);
        if (validate()) {
            String qLocale = generateQueryLocale();
            String qRows = "&rows=" + iRows;
            String qStart = "&start=" + iStart;
            String solrquery = query + qLocale + qRows + qStart;
            Map<String, String> ctxt = loadCtxt();
            SgSolrJson solr = new SgSolrJson(request, ctxt);
            jRes = solr.searchSolrFields(solrquery);
        }
    } catch (Exception e) {
        jRes = SgSolrJson.errorJResponse(e);
    } finally {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jRes.toString());
    }
%>