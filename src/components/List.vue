<template>
    <div id="list" class="col-lg-12 nopadding">
        <ul>
            <li v-for="vData in vDatas" :ref="vData.id">
                <v-list-title :vData="vData"></v-list-title>
            </li>
        </ul>
    </div>
</template>

<script>
    import ListTitle from './ListTitle.vue'

    export default
    {
        props:{
            vDatas:{
                type: Array,
                required: true
            }
        },
        components:{
            'v-list-title': ListTitle
        }
    }
</script>

<style>
</style>
