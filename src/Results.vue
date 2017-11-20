<template>
    <div :id="id" class="results">
        <p>id: {{id}}</p>
        <p>locale: {{vLocale}}</p>
        <p>rows: {{vRows}}</p>
        <p>start: {{vStart}}</p>
        <p>controller: {{vController}}</p>
        <p>query: {{vQuery}}</p>
        <p>filters: {{vFilters}}</p>
        <p>search: GET {{ vController }}?q={{ query }}&filters={{ vFilters }}</p>
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
            filters.$on('filtersChanged', () => {
                console.log('$on filtersChanged', filters._vnode.data.props.vFilters);
                this.vFilters = filters._vnode.data.props.vFilters;
            });
        }

    }
</script>

<style>
</style>
