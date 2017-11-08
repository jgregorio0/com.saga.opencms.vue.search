<template>
    <v-filter-text v-if="filter.type == 'TextFilter' && !filter.isPrincipal" :filter="filter"></v-filter-text>
    <v-filter-text-principal v-else-if="filter.type == 'TextFilter' && filter.isPrincipal" :filter="filter"></v-filter-text-principal>
    <v-filter-category v-else-if="filter.type == 'CategoryFilter'" :filter="filter"></v-filter-category>
    <v-filter-date v-else-if="filter.type == 'Date1Filter'" :filter="filter"></v-filter-date>
    <v-filter-date-between v-else-if="filter.type == 'Date2Filter'" :filter="filter"></v-filter-date-between>
</template>

<script>
    import FilterText from './FilterText.vue'
    import FilterTextPrincipal from './FilterTextPrincipal.vue'
    import FilterCategory from './FilterCategory.vue'
    import FilterDate from './FilterDate.vue'
    import FilterDateBetween from './FilterDateBetween.vue'

    export default
    {
        props:{
            filter:{
                type: Object,
                required: true
            }
        },
        components:{
            'v-filter-text': FilterText,
            'v-filter-text-principal': FilterTextPrincipal,
            'v-filter-category': FilterCategory,
            'v-filter-date': FilterDate,
            'v-filter-date-between': FilterDateBetween
        }
    }
</script>

<style>
</style>
