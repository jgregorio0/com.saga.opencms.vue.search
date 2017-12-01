<template>
    <div :id="id" class="vFilters" >
        <v-filter v-for="(vFilter, index) in vFilters" :vFilter="vFilter" :key="vFilter.id"></v-filter>
    </div>
</template>

<script>
    import Filter from './components/Filter.vue'

    export default {
        props:{
            id:{
                required: true
            },
            pLocale:{
                required: true
            },
            pFilters:{
                required: true,
                type: Array
            }
        },
        data(){
            return {
                vFilters: this.pFilters.slice()
            }
        },
        components:{
            'v-filter': Filter
        }
    }
</script>

<style>
</style>
