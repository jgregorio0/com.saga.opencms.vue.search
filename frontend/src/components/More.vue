<template>
    <div id="vue-list-more" class="col-lg-12 nopadding mt-20 mb-20 text-center"
         v-if="!vLoading">

        <!--MORE RESULTS-->
        <button type="button" class="btn btn-brand btn-lg vue-list-more-btn"
            v-if="pRows + vStart < vTotal"
        @click="loadMoreResults()">
            <span class="inline-b v-align-m">
                <!--<fmt:message key="search.more.results.btn"/>-->More results
            </span>
            <span class="fa fa-chevron-down inline-b v-align-m ml-5" aria-hidden="true"></span>
        </button>

        <!--NONE RESULTS-->
        <div class="btn btn-brand"
             v-else-if="vTotal == 0">
                <span class="inline-b v-align-m">
                    <!--<fmt:message key="search.more.results.none"/>-->There are no results
                </span>
        </div>
    </div>
</template>

<script>
    import {events} from '../main.js'

    export default
    {
        props:{
            vLoading:{
                type: Boolean,
                required: true
            },
            pRows:{
                type: Number,
                required: true
            },
            vStart:{
                type: Number,
                required: true
            },
            vTotal:{
                type: Number,
                required: true
            }
        },
        methods:{
            loadMoreResults(){
                events.$emit('loadMoreResults');
            }
        }
    }
</script>

<style>
</style>
