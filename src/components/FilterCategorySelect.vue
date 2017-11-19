<!--FILTER CATEGORY SELECT
-----------

"id": "${filterName}"
,"count": ${status.count}
,"type": "CategoryFilter"
<c:if test="${elem.value.Label.isSet}">,"label": "${elem.value.Label}"</c:if>
<c:if test="${elem.value.Placeholder.isSet}">,"placeholder": "${elem.value.Placeholder}"</c:if>
,"solr": "${elem.value.FieldSolr}"
,"class": "${filterClass}"
,"categoryRoot": "${CategoryRoot}"
,"treeFolder": "${TreeFolder}"
,"showParent": "${ShowParent}"
,"showEmptyOption": "${!elem.value.ShowEmptyOption.exists or elem.value.ShowEmptyOption=='true'}"
,"labelCategoryFilterAll": "${LabelCategoryFilterAll}"
,"fieldType": "${elem.value.FieldType}"
,"categories": ${categoriesFilter}
,"value": "${filterValue}"
-->

<template>
    <div :class="'form-group filter filter-cat ' + vFilter.class" :id="'filter-' + vFilter.count">
        <label :for="vFilter.id"
               v-if="vFilter.label">{{vFilter.label}}</label>
        <label :for="vFilter.id" class="sr-only"
               v-else>{{vFilter.id}}</label>

        <!--SELECT-->
        <div class="block-drop"
             v-if="vFilter.fieldType == 'select'">
            <select class="form-control" :name="vFilter.id" :id="vFilter.id"
                    v-model="vFilter.value" @input="filtersChanged">
                <option disabled value="" class="nivel-0"
                        v-if="vFilter.showEmptyOption"></option>
                <option v-for="category in vFilter.categories"
                        :value="category.value" :class="category.class">{{category.title}}</option>
            </select>
        </div>
    </div>
</template>

<script>
    import {filters} from '../main'

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
                filters.$emit('filtersChanged');
            }
        }
    }
</script>

<style>
</style>
