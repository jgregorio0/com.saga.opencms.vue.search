<template>
    <v-filter-text v-if="vFilter.type == 'TextFilter' && !vFilter.isPrincipal" :vFilter="vFilter"></v-filter-text>
    <v-filter-text-principal v-else-if="vFilter.type == 'TextFilter' && vFilter.isPrincipal" :vFilter="vFilter"></v-filter-text-principal>
    <v-filter-category v-else-if="vFilter.type == 'CategoryFilter'" :vFilter="vFilter"></v-filter-category>
    <v-filter-date v-else-if="vFilter.type == 'Date1Filter'" :vFilter="vFilter"></v-filter-date>
    <v-filter-date-between v-else-if="vFilter.type == 'Date2Filter'" :vFilter="vFilter"></v-filter-date-between>
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
            vFilter:{
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
