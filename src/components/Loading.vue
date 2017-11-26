<template>
    <div id="vue-list-loading" class="col-lg-1  2 nopadding text-center"
         v-if="vLoading">
                <span>
                    <span class="fa fa-spinner fa-spin fa-5x"></span>
                        <!--<fmt:message key="search.loading"/>-->Loading
                    <span class="sr-only"><!--<fmt:message key="search.loading"/>-->Loading</span>
                </span>
    </div>
</template>

<script>
    export default
    {
        props:{
            vLoading:{
                type: Boolean,
                required: true
            }
        }
    }
</script>

<style>
</style>
