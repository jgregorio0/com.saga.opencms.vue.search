<template>
    <div :id="id" class="results">
        <p>id: {{id}}</p>
        <p>locale: {{locale}}</p>
        <p>rows: {{rows}}</p>
        <p>start: {{start}}</p>
        <p>controller: {{controller}}</p>
        <p>query: {{query}}</p>
        <p>filters: {{filters}}</p>
    </div>
</template>

<script>
    import {filters} from './main.js'

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
            return {vFilters: []}
        },
        created(){
            this.filters = filters.$children[0].filters;/*
            filters.$on('changeFilters', (filters) => {
                console.log('$on changeFilters', filters);
                this.filters = filters;
            });*/
        }

    }
</script>

<style>
</style>
