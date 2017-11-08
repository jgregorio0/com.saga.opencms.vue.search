<template>
    <div :id="id" class="filters" >
        <v-filter v-for="(filter, index) in filters" :filter="filter"></v-filter>
    </div>
</template>

<script>
    import Filter from './components/Filter.vue'
    export default {
        props:{
            id:{
                required: true
            },
            locale:{
                required: true
            },
            filters:{
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
