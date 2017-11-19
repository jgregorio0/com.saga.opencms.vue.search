<template>
    <div :id="id" class="vFilters" >
        <v-filter v-for="(vFilter, index) in vFilters" :vFilter="vFilter"></v-filter>
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
        },
        methods:{
            changeFilters(){
                console.log('filters has been changed', this.filters)
            }
        }
    }
</script>

<style>
</style>
