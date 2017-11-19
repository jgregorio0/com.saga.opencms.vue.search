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
            vLocale:{
                required: true
            },
            vFilters:{
                required: true,
                type: Array
            }
        },
        components:{
            'v-filter': Filter
        }
    }
</script>

<style>
</style>
