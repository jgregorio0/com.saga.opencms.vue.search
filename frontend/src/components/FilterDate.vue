<!--FILTER TEXT PRINCIPAL
---------------------

"id": "${value.Id}-filter-${status.count}"
"count": ${status.count}
"type": "${elem.name}"
"label": "${elem.value.Label}"
"placeholder": "${elem.value.Placeholder}"
"solr": "${elem.value.FieldSolr}"
"class": "${filtercssclass}"
"isContainsSearch": ${elem.value.ContainsSearch}
"isPrincipal": true
"value": ""
}-->

<template>
    <div :class="'form-group ' + vFilter.class + ' filter filter-date'" :id="'filter-' + vFilter.count">
        <label :for="vFilter.id"
               v-if="vFilter.label">{{vFilter.label}}</label>
        <label :for="vFilter.id" class="sr-only"
               v-else>{{vFilter.id}}</label>
        <input type="text" :name="vFilter.id" :id="vFilter.id" class="form-control datepicker"
               v-model="vFilter.value" @input="filtersChanged">
    </div>
</template>

<script>
    import {events} from '../main'

    export default
    {
        props:{
            vFilter:{
                type: Object,
                required: true
            }
        },
        methods: {
            filtersChanged(){
                events.$emit('filtersChanged');
            }
        }
    }
</script>

<style>
</style>
