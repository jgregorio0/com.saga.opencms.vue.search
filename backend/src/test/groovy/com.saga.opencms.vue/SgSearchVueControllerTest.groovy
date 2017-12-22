package com.saga.opencms.vue

import groovy.json.JsonParserType
import groovy.json.JsonSlurper
import org.apache.commons.lang3.StringUtils
import org.junit.Test
import org.opencms.json.JSONException
import org.opencms.json.JSONObject
import org.opencms.main.CmsException

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by jgregorio on 21/12/2017.
 */
class SgSearchVueControllerTest {

    //Filter Text
    //private String payLoad = '''{"params": {"start": 0,"query": "&fq=type:(%22image%22)&fq=con_locales:en&fq=parent-folders:(%22/sites/default/%22)","locale": "en","filters": "%5B%7B%22id%22:%22buscador-field-1%22,%22count%22:1,%22type%22:%22TextFilter%22,%22label%22:%22Texto%20Principal%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22class%22:%22col-sm-12%22,%22isExactSearch%22:false,%22isContainsSearch%22:false,%22isPrincipal%22:true,%22value%22:%22hola%22,%22showExactSearch%22:true,%22labelExactResult%22:%22B%C3%BAsqueda%20exacta%22,%22buttonText%22:%22Buscar%22,%22buttonClass%22:%22btn-default%22%7D,%7B%22id%22:%22buscador-field-2%22,%22count%22:2,%22type%22:%22TextFilter%22,%22label%22:%22Texto%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22isPrincipal%22:false,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-3%22,%22count%22:3,%22type%22:%22Date1Filter%22,%22label%22:%22Fecha%20simple%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-4%22,%22count%22:4,%22type%22:%22Date2Filter%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22labelDate1%22:%22Desde:%22,%22valueDate1%22:%22%22,%22labelDate2%22:%22Hasta:%22,%22valueDate2%22:%22%22%7D,%7B%22id%22:%22buscador-field-5%22,%22count%22:5,%22type%22:%22CategoryFilter%22,%22label%22:%22Categoria%22,%22solr%22:%22%22,%22class%22:%22col-sm-12%22,%22categoryRoot%22:%22/.categories/topic/%22,%22treeFolder%22:%22true%22,%22showParent%22:%22title%22,%22showEmptyOption%22:%22false%22,%22labelCategoryFilterAll%22:%22Todas%22,%22fieldType%22:%22select%22,%22categories%22:%5B%7B%22title%22:%22Topic%22,%22class%22:%22nivel-0%22,%22value%22:%22topic/%22%7D,%7B%22title%22:%22%20%20%20Buildings%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/buildings/%22%7D,%7B%22title%22:%22%20%20%20Machines%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/machines/%22%7D,%7B%22title%22:%22%20%20%20Nature%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/nature/%22%7D,%7B%22title%22:%22%20%20%20People%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/people/%22%7D,%7B%22title%22:%22%20%20%20Transportation%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/transportation/%22%7D%5D,%22value%22:%22%22%7D%5D","rows": 10}}''';

    // Filter text and date
    private String payLoad = '''{"params": {"start": 0,"query": "&fq=type:(%22image%22)&fq=con_locales:en&fq=parent-folders:(%22/sites/default/%22)","locale": "en","filters": "%5B%7B%22id%22:%22buscador-field-1%22,%22count%22:1,%22type%22:%22TextFilter%22,%22label%22:%22Texto%20Principal%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22class%22:%22col-sm-12%22,%22isExactSearch%22:false,%22isContainsSearch%22:false,%22isPrincipal%22:true,%22value%22:%22hola%22,%22showExactSearch%22:true,%22labelExactResult%22:%22B%C3%BAsqueda%20exacta%22,%22buttonText%22:%22Buscar%22,%22buttonClass%22:%22btn-default%22%7D,%7B%22id%22:%22buscador-field-2%22,%22count%22:2,%22type%22:%22TextFilter%22,%22label%22:%22Texto%22,%22placeholder%22:%22Enter%20a%20word%20or%20phrase%20to%20search%22,%22solr%22:%22content%22,%22isPrincipal%22:false,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-3%22,%22count%22:3,%22type%22:%22Date1Filter%22,%22label%22:%22Fecha%20simple%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22value%22:%22%22%7D,%7B%22id%22:%22buscador-field-4%22,%22count%22:4,%22type%22:%22Date2Filter%22,%22solr%22:%22contentdate%22,%22class%22:%22col-sm-12%22,%22labelDate1%22:%22Desde:%22,%22valueDate1%22:%22%22,%22labelDate2%22:%22Hasta:%22,%22valueDate2%22:%22%22%7D,%7B%22id%22:%22buscador-field-5%22,%22count%22:5,%22type%22:%22CategoryFilter%22,%22label%22:%22Categoria%22,%22solr%22:%22%22,%22class%22:%22col-sm-12%22,%22categoryRoot%22:%22/.categories/topic/%22,%22treeFolder%22:%22true%22,%22showParent%22:%22title%22,%22showEmptyOption%22:%22false%22,%22labelCategoryFilterAll%22:%22Todas%22,%22fieldType%22:%22select%22,%22categories%22:%5B%7B%22title%22:%22Topic%22,%22class%22:%22nivel-0%22,%22value%22:%22topic/%22%7D,%7B%22title%22:%22%20%20%20Buildings%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/buildings/%22%7D,%7B%22title%22:%22%20%20%20Machines%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/machines/%22%7D,%7B%22title%22:%22%20%20%20Nature%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/nature/%22%7D,%7B%22title%22:%22%20%20%20People%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/people/%22%7D,%7B%22title%22:%22%20%20%20Transportation%22,%22class%22:%22nivel-1%22,%22value%22:%22topic/transportation/%22%7D%5D,%22value%22:%22%22%7D%5D","rows": 10}}''';

    static class LOG{
        static def debug(def s){
            println "DEBUG -- " + s.toString()
        }
        static def error(def s){
            println "ERROR -- " + s.toString()
        }
        static def error(def s, def e){
            println "ERROR -- " + s.toString()
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

    private DateFormat df;
    private DateFormat dfSolr;

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
        LOG.debug("filters: " + filters);

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

    /**
     *
     * @param paramName
     * @return
     */
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

        def jFilters = new JsonSlurper().setType(JsonParserType.INDEX_OVERLAY).parseText(filters)
        jFilters.each{ filter ->
            try {
                String qFilter = generateQueryFilter(filter)
                if (StringUtils.isNotBlank(qFilter)) {
                    qFilters.append(qFilter);
                }
            } catch (Exception e) {
                LOG.error("cannot generate query for filter $filter", e);
            }
        }
        return qFilters.toString();
    }

    // "type": "TextFilter" ,"label": "Texto Principal","placeholder": "Enter a word or phrase to search" ,"solr": "content" ,"class": "col-sm-12" ,"isExactSearch": false ,"isContainsSearch": false ,"isPrincipal": true ,"value": "hola" ,"showExactSearch": true ,"labelExactResult": "Búsqueda exacta" ,"buttonText": "Buscar" ,"buttonClass": "btn-default" }, { "id": "buscador-field-2" ,"count": 2 ,
    // type": "TextFilter" ,"label": "Texto","placeholder": "Enter a word or phrase to search" ,"solr": "content" ,"isPrincipal": false ,"class": "col-sm-12" ,"value": "" }, { "id": "buscador-field-3" ,"count": 3 ,"
    // type": "Date1Filter" ,"label": "Fecha simple" ,"solr": "contentdate" ,"class": "col-sm-12" ,"value": "" }, { "id": "buscador-field-4" ,"count": 4 ,"
    // type": "Date2Filter" ,"solr": "contentdate" ,"class": "col-sm-12" ,"labelDate1": "Desde:" ,"valueDate1": "" ,"labelDate2": "Hasta:" ,"valueDate2": "" }, { "id": "buscador-field-5" ,"count": 5 ,"
    // type": "CategoryFilter" ,"label": "Categoria" ,"solr": "" ,"class": "col-sm-12" ,"categoryRoot": "/.categories/topic/" ,"treeFolder": "true" ,"showParent": "title" ,"showEmptyOption": "false" ,"labelCategoryFilterAll": "Todas" ,"fieldType": "select" ,"categories": [{"title":"Topic","class":"nivel-0","value":"topic/"}, {"title":"   Buildings","class":"nivel-1","value":"topic/buildings/"}, {"title":"   Machines","class":"nivel-1","value":"topic/machines/"}, {"title":"   Nature","class":"nivel-1","value":"topic/nature/"}, {"title":"   People","class":"nivel-1","value":"topic/people/"}, {"title":"   Transportation","class":"nivel-1","value":"topic/transportation/"}] ,"value": "" }]'>
    String generateQueryFilter(def filter) {
        LOG.debug("generateQueryFilter: " + filter)

        switch (filter.type){
            case "TextFilter":
                return generateQueryTextFilter(filter)
            case "Date1Filter":
                return generateQueryDateFilter(filter)
            default:
                throw new FilterTypeException("Case for filter type " + filter.type + " does not exist")
        }
    }

    /**
     * Generate query for date filter. Date1Filter {value, solr}. Date2Filter {valueDate1, valueDate2, solr}
     * @param filter
     * @return
     */
    String generateQueryDateFilter(def filter) {
        if (filter.type != "Date1Filter" && filter.type != "Date2Filter") {
            throw new FilterTypeException("Filter type is not Date1Filter or Date2Filter for " + filter)
        }

        String q;
        if (validateDateFilter(filter)) {
            switch (filter.type){
                case "Date1Filter":
                    q = generateQueryDate1FilterValue(filter.value, filter.solr)
                    break;
                case "Date2Filter":
                    q = generateQueryDate2FilterValue(filter.valueDate1, filter.valueDate2, filter.solr)
                    break;
                default:
                    throw new FilterTypeException("Filter type is not Date1Filter or Date2Filter for " + filter)
            }
        }
        return q;
    }

    /**
     * Generate query date from filter value
     * Date1Filter {value, solr}.
     * @param fieldValue
     * @param solrField
     * @return
     */
    def generateQueryDate1FilterValue(String fieldValue, String solrField) {
        initDateFmt()
        Date d = df.parse(fieldValue);
        Date d2 = new Date(d.getTime() + (24 * 60 * 60 * 1000)); //1 dia despues
        query += "&fq=" + solrField + ":[" + dfSolr.format(d) + " TO " + dfSolr.format(d2) + "]";
    }

    /**
     * Generate query date from and to filter value
     * Date2Filter {valueDate1, valueDate2, solr}
     * @param from
     * @param to
     * @param solrField
     * @return
     */
    def generateQueryDate2FilterValue(String from, String to, String solrField) {
        String val = "";
        initDateFmt();
        if ((from != null
                && (from.length() == 10
                        || from.equals("NOW")
                        || from.equals("*")))
                || (to != null
                        && (to.length() == 10)
                        || to.equals("NOW")
                        || to.equals("*"))) {

            String fromValue;
            if (from != null && (from.indexOf("NOW") > -1 || from.equals("*"))) {
                fromValue = from;
            } else if (from != null && from.length() == 10) {
                Date d1 = df.parse(from);
                fromValue = dfSolr.format(d1);
            } else {
                fromValue = "*";
            }

            String toValue;
            if (to != null && (to.indexOf("NOW") > -1 || to.equals("*"))) {
                toValue = to;
            } else if (to != null && to.length() == 10) {
                Date d2 = df.parse(to);
                toValue = dfSolr.format(d2);
            } else {
                toValue = "*";
            }
            val += "&fq=" + solrField + ":[" + fromValue + " TO " + toValue + "]";
        }
    }

    /**
     * init input date format and Solr date format
     */
    void initDateFmt() {
        if (!df) {
            df = new SimpleDateFormat("dd/MM/yyyy");
        }
        if (!dfSolr) {
            dfSolr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            dfSolr.setTimeZone(TimeZone.getTimeZone("UTC"));
        }
    }

    /**
     * Validate date filter fields. Date1Filter {value, solr}. Date2Filter {valueDate1, valueDate2, solr}
     * @param filter
     * @return
     */
    boolean validateDateFilter(def filter) {
        if (StringUtils.isBlank(filter.value) ||
                (StringUtils.isBlank(filter.valueDate1) && StringUtils.isBlank(filter.valueDate2))) {
            return false;
        }
        if (StringUtils.isBlank(filter.solr)) {
            throw new FilterValidationException("filter solr field must not be empty: " + filter)
        }
        return true;
    }

    /**
     * Generate query for text filter.
     * TextFilter{solr, isExactSearch, isContainsSearch, value}
     * @param filter
     * @return
     */
    def generateQueryTextFilter(def filter) {
        if (filter.type != "TextFilter") {
            throw new FilterTypeException("Filter type is not TextFilter for " + filter)
        }

        String q = "";
        if (validateTextFilter(filter)) {
            String solr = filter.solr;
            String value = generateQueryTextFilterValue(
                    filter.value, filter.isExactSearch, filter.isContainsSearch)
            q = "&q=$solr:($value)"
        }

        return q;
    }

    /**
     * Generate query text filter value
     * @param value
     * @param isExactSearch
     * @param isContainsSearch
     * @return
     */
    private String generateQueryTextFilterValue(
            String value, boolean isExactSearch, boolean isContainsSearch) {
        String val;
        if (isExactSearch) {
            val = "\"" + value + "\"";
        } else if (isContainsSearch) {
            val = "*" + value + "*";
        } else {
            val = value;
        }
        return val;
    }

    /**
     * Validate text filter fields
     * @param filter
     * @return
     */
    boolean validateTextFilter(def filter) {
        if (StringUtils.isBlank(filter.value)) {
            return false;
        }
        if (StringUtils.isBlank(filter.solr)) {
            throw new FilterValidationException("filter solr field must not be empty: " + filter)
        }
        return true;
    }

    /**
     * Exception for filter type wrong value
     */
    class FilterTypeException extends Exception{
        FilterTypeException(){
            super()
        }
        FilterTypeException(String s){
            super(s)
        }
    }

    /**
     * Exception for filter fields validation
     */
    class FilterValidationException extends Exception{
        FilterValidationException(){
            super()
        }
        FilterValidationException(String s){
            super(s)
        }
    }
}