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
    <div :class="'form-group filter filter-cat ' + filter.class" :id="'filter-' + filter.count">
        <label :for="filter.id"
               v-if="filter.label">{{filter.label}}</label>
        <label :for="filter.id" class="sr-only"
               v-else>{{filter.id}}</label>

        <!--SELECT-->
        <div class="block-drop"
             v-if="filter.fieldType == 'select'">
            <select class="form-control" :name="filter.id" :id="filter.id"
                    v-model="filter.value">
                <option disabled value="" class="nivel-0"
                        v-if="filter.showEmptyOption"></option>
                <option v-for="category in filter.categories"
                        :value="category.value" :class="category.class">{{category.title}}</option>
            </select>
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
