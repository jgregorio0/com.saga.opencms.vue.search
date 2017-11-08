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
            locale:{
                required: true
            },
            rows:{
                required: true,
                type: Number
            },
            start:{
                required: true,
                type: Number
            },
            controller:{
                required: true
            },
            query:{
                required: true
            }
        },
        data: function(){
            return {filters: []}
        },
        created(){
            filters.$on('changeFilter', (filter) => {
                console.log('$on changeFilter', filter);
                this.filters = [filter];
            });
        }

    }
</script>

<style>
</style>
