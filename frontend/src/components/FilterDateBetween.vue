<!--FILTER DATE FROM AND TO
---------------------

"id": "${value.Id}-filter-${status.count}"
"count": ${status.count}
"type": "${elem.name}"
"label": "${elem.value.Label}"
"placeholder": "${elem.value.Placeholder}"
"solr": "${elem.value.FieldSolr}"
"class": "${filtercssclass}"
"labelDate1": "${elem.value.LabelDate1 }"
"valueDate1": ""
"labelDate2": "${elem.value.LabelDate2 }"
"valueDate2": ""
}-->

<template>
    <div :class="'form-group filter filter-date filter-date-multiple form-inline ' + vFilter.class">
        <div class="row">
            <div class="form-group col-xs-6" :id="'filter-' + vFilter.count">
                <label :for="vFilter.id + '_d1'"
                       v-if="vFilter.labelDate1">{{vFilter.labelDate1}}</label>
                <label :for="vFilter.id + '_d1'" class="sr-only"
                       v-else>{{vFilter.id}}_d1</label>
                <input type="text" :name="vFilter.id + '_d1'" :id="vFilter.id + '_d1'"
                       class="form-control datepicker datepicker-1"
                       v-model="vFilter.valueDate1" @input="filtersChanged">
            </div>
            <div class="form-group col-xs-6" :id="'filter-' + vFilter.count">
                <label :for="vFilter.id + '_d2'"
                       v-if="vFilter.labelDate2">{{vFilter.labelDate2}}</label>
                <label :for="vFilter.id + '_d2'" class="sr-only"
                       v-else>{{vFilter.id}}_d2</label>
                <input type="text" :name="vFilter.id + '_d2'" :id="vFilter.id + '_d2'"
                       class="form-control datepicker datepicker-2"
                       v-model="vFilter.valueDate2" @input="filtersChanged">
            </div>
        </div>
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
