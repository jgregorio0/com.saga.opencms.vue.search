package com.saga.opencms.vue
import org.apache.commons.lang3.StringUtils
import org.junit.Test
import org.opencms.json.JSONException
import org.opencms.json.JSONObject
import org.opencms.main.CmsException
/**
 * Created by jgregorio on 21/12/2017.
 */
class SgSearchVueControllerTest {

    private String payLoad = '''{
      "params": {
        "start": 0,
        "query": "&fq=type:(%22image%22)&fq=con_locales:en&fq=parent-folders:(%22/sites/default/%22)",
        "locale": "en",
        "filters": "%5B%7B%22id%22:%22buscador-field-1%22,%22count%22:1,%22type%22:%22TextFilter%22,%22label%22:%22Texto%20Principal%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22class%22:%22col-sm-12%22,%22isExactSearch%22:false,%22isContainsSearch%22:false,%22isPrincipal%22:true,%22value%22:%22hola%22,%22showExactSearch%22:true,%22labelExactResult%22:%22B%C3%BAsqueda%20exacta%22,%22buttonText%22:%22Buscar%22,%22buttonClass%22:%22btn-default%22%7D,%7B%22id%22:%22buscador-field-2%22,%22count%22:2,%22type%22:%22TextFilter%22,%22label%22:%22Texto%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22isPrincipal%22:false,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-3%22,%22count%22:3,%22type%22:%22Date1Filter%22,%22label%22:%22Fecha%20simple%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-4%22,%22count%22:4,%22type%22:%22Date2Filter%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22labelDate1%22:%22Desde:%22,%22valueDate1%22:%22%22,%22labelDate2%22:%22Hasta:%22,%22valueDate2%22:%22%22%7D,%7B%22id%22:%22buscador-field-5%22,%22count%22:5,%22type%22:%22CategoryFilter%22,%22label%22:%22Categoria%22,%22solr%22:%22%22,%22class%22:%22col-sm-12%22,%22categoryRoot%22:%22/.categories/topic/%22,%22treeFolder%22:%22true%22,%22showParent%22:%22title%22,%22showEmptyOption%22:%22false%22,%22labelCategoryFilterAll%22:%22Todas%22,%22fieldType%22:%22select%22,%22categories%22:%5B%7B%22title%22:%22Topic%22,%22class%22:%22nivel-0%22,%22value%22:%22topic/%22%7D,%7B%22title%22:%22%20%20%20Buildings%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/buildings/%22%7D,%7B%22title%22:%22%20%20%20Machines%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/machines/%22%7D,%7B%22title%22:%22%20%20%20Nature%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/nature/%22%7D,%7B%22title%22:%22%20%20%20People%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/people/%22%7D,%7B%22title%22:%22%20%20%20Transportation%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/transportation/%22%7D%5D,%22value%22:%22%22%7D%5D",
        "rows": 10
      }
    }''';

    static class LOG{
        static def debug(def s){
            println "DEBUG -- " + s.toString()
        }
        static def error(def s){
            println "ERROR -- " + s.toString()
        }
        static def error(def s, def e){
            println "DEBUG -- " + s.toString()
            println CmsException.getStackTraceAsString(e)
        }
    }


    private JSONObject params;

    private String locale;
    private String site;
    private String uri;
    private String index;

    private String query;
    private String filters;
//    private String fields;
    private int iRows;
    private int iStart;

    @Test
    public void load(){

        params = new JSONObject(payLoad).getJSONObject(SgSearchVueController.PAYLOAD_PARAMS);
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

        // filters
        String filtersParam = getParam("filters");
        if (StringUtils.isNotBlank(filtersParam)) {
            filters = URLDecoder.decode(filtersParam, "UTF-8");
        }

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

    private String getParam(String paramName) {
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


    private String generateQueryFilters() {
        StringBuffer qFilters = new StringBuffer();

        filters.each{ filter ->
            try {
                String qFilter = generateQueryFilter(filter)
                qFilter.append(qFilter);
            } catch (Exception e) {
                LOG.error("cannot generate query for filter $filter");
            }
        }
    }


    generateQueryFilter(def filter){
        switch (filter.type){
            case "TextFilter":
                return "q=${filter.solr}:${filter.value}":
                break;
        }
    }


    @Test
    void testController(){
        JSONObject jRes = new JSONObject();
        try {
            load();
            if (validate()) {
                String qLocale = generateQueryLocale();
                LOG.debug("qLocale: " + qLocale);

                String qRows = "&rows=" + iRows;
                LOG.debug("qRows: " + qRows);

                String qStart = "&start=" + iStart;
                LOG.debug("qStart: " + qStart);

                String qFilters = generateQueryFilters();
                LOG.debug("qFilters: " + qFilters);

                String solrquery = query + qLocale + qRows + qStart;
                LOG.debug("solrquery: " + solrquery);

//                Map<String, String> ctxt = loadCtxt();
//                SgSolrJson solr = new SgSolrJson(request, ctxt);
//                jRes = solr.searchSolrFields(solrquery);
            }
        } catch (Exception e) {
            jRes = SgSolrJson.errorJResponse(e);
        } finally {
            println jRes.toString()
        }
    }

}
