<template>
    <div :id="id" class="results row"
         v-cloak>
        
        <!--LIST-->
        <v-list :vDatas="vDatas"></v-list>
        

        <!--MORE BTN-->
        <v-more :vLoading="vLoading" :vRows="vRows" :vStart="vStart" :vTotal="vTotal"></v-more>

        <!--LOADING-->
        <v-loading :vLoading="vLoading"></v-loading>
    </div>
</template>

<script>
    import {filters} from './main.js'
    import List from './components/List.vue'
    import More from './components/More.vue'
    import Loading from './components/Loading.vue'

    export default {
        props:{
            id:{
                required: true
            },
            vLocale:{
                required: true
            },
            vRows:{
                required: true,
                type: Number
            },
            vStart:{
                required: true,
                type: Number
            },
            vController:{
                required: true
            },
            vQuery:{
                required: true
            }
        },
        data: function(){
            return {
                vFilters: [],
                vLoading: false,
                vTotal: 0,
                vDatasSize: 0,
                vDatas: [],
                vFields: "",
            }
        },
        components:{
            "v-list": List,
            "v-more": More,
            "v-loading": Loading
        },
        created(){
            filters.$on('filtersChanged', () => {
                console.log('$on filtersChanged', filters._vnode.data.props.vFilters);
                this.vFilters = filters._vnode.data.props.vFilters;
            });
        },
        mounted(){
            this.loadResults();
        },
        methods: {

            /**
             * Load data from main container, post controller to get results and post template for print them.
             * start === 0 means first loading.
             */
            loadResults: function () {
                console.log("loadResults", this.vStart);

                if (this.validateContext()) {

                    // load list datas
                    this.getResults();
                }
            },

            /**
             * Validate controller, locale and rows
             *
             */
            validateContext: function () {
                console.debug("validateContext", this.vController, this.vLocale, this.vRows);

                // controller
                if (!this.vController) {
                    throw Error("controller undefined");
                }

                // locale
                if (!this.vLocale) {
                    throw Error("locale undefined");
                }

                // rows
                if (this.vRows === undefined) {
                    throw Error("rows undefined");
                }

                return true;
            },

            /**
             * Post controller to get results
             * GET {"controller.jsp", {"locale": "es", "rows": 10, "start": 0, "query": "fq=...", "filters": "date..."}
             * RESPONSE {"error": true/false, "errorMsg": "...", "errorTrace": "....", "dataSize": 10, "data": []}
             */
            getResults: function () {
                console.debug("getResults");

                var params = this.getResultsParams();

                if (this.validateGetResultsParams(params)) {

                    // Show loading
                    this.vLoading = true;

                    // Load data from controller
                    ((this.$http.get(this.vController, {params: params}).then(function (response) {
                        // Response to JSON
                        try {
                            if (response.status !== 200) {
                                throw Error("Response failed");
                            }

                            var jRes = response.body;

                            // Check if error
                            if (!jRes.error) {

                                // If validate then update
                                this.update(jRes);
                            } else {
                                console.error("JSON Response", jRes.errorMsg, jRes.errorTrace);
                            }
                        } catch (err) {
                            console.error('loading data', params, response, err);
                        } finally {
                            this.vLoading = false;
                        }
                    }, function (err) {
                        console.error("POST controller", params, err);

                        // Hide loading and show results
                        this.vLoading = false;
                    })));
                }
            },

            /**
             * Load params for update list
             */
            getResultsParams: function () {
                console.debug("getResultsParams", this.vLocale, this.vRows, this.vStart, this.vQuery, this.vFilters);
                var params = {};

                // params
                params.locale = this.vLocale;
                params.rows = this.vRows;
                params.start = this.vStart;
                params.query = encodeURI(this.vQuery);
                params.filters = encodeURI(JSON.stringify(this.vFilters));

                return params;
            },

            /**
             * Validate attributes and params
             *
             * @param params
             */
            validateGetResultsParams: function (params) {
                console.debug("validateGetResultsParams", this.vController, params.locale, params.start);
                // controller
                if (!this.vController) {
                    throw Error("controller must be defined")
                }

                // locale
                if (!params.locale) {
                    throw Error("locale must be defined")
                }

                // start
                if (params.start === undefined) {
                    throw Error("start must be defined")
                }

                return true;
            },

            /**
             * Update attributes after receiving json response
             */
            update: function (jRes) {
                console.debug("update", jRes);
                this.vTotal = Number(jRes.total);
                this.vDatasSize = Number(jRes.dataSize);
                if (this.vStart == 0) {
                    this.vDatas = jRes.data;
                } else {
                    this.vDatas = this.vDatas.concat(jRes.data);
                }

                //this.vQuery = jRes.query;
                this.vFields = jRes.vFields;
            },

            /**
             * Load data from main container, post controller to get results and post template for print them.
             * start === 0 means first loading.
             */
            loadMoreResults: function () {
                this.vStart = (this.vStart + this.vRows);
                this.loadResults();
            },

            /**
             * Load data from main container, post controller to get results and post template for print them.
             * start === 0 means first loading.
             */
            initResults: function () {
                    this.vStart = 0;
                    this.loadResults();
                }
        }
    }
</script>

<style>
    /*[v-cloak] {*/
        /*display: none;*/
    /*}*/
</style>
