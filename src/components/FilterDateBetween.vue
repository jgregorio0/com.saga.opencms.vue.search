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
    <div :class="'form-group filter filter-date filter-date-multiple form-inline ' + filter.class">
        <div class="row">
            <div class="form-group col-xs-6" :id="'filter-' + filter.count">
                <label :for="filter.id + '_d1'"
                       v-if="filter.labelDate1">{{filter.labelDate1}}</label>
                <label :for="filter.id + '_d1'" class="sr-only"
                       v-else>{{filter.id}}_d1</label>
                <input type="text" :name="filter.id + '_d1'" :id="filter.id + '_d1'"
                       class="form-control datepicker datepicker-1"
                       v-model="filter.valueDate1">
            </div>
            <div class="form-group col-xs-6" :id="'filter-' + filter.count">
                <label :for="filter.id + '_d2'"
                       v-if="filter.labelDate2">{{filter.labelDate2}}</label>
                <label :for="filter.id + '_d2'" class="sr-only"
                       v-else>{{filter.id}}_d2</label>
                <input type="text" :name="filter.id + '_d2'" :id="filter.id + '_d2'"
                       class="form-control datepicker datepicker-2"
                       v-model="filter.valueDate2">
            </div>
        </div>
    </div>
</template>

<script>
    export default
    {
        props:{
            filter:{
                type: Object,
                required: true
            }
        },
        watch: {
            filter(){
                console.log('filter changed',this.filter);
                this.$emit('changeFilter', this.filter);
            }
        }
    }
</script>

<style>
</style>
