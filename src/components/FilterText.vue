<template>
    <div :class="'form-group filter filter-text ' + vFilter.class" :id="'filter-' + vFilter.count">
        <label :for="vFilter.id"
               v-if="vFilter.label">{{vFilter.label}}</label>
        <label :for="vFilter.id" class="sr-only"
               v-else>{{vFilter.placeholder}}</label>
        <input type="text" class="form-control" :placeholder="vFilter.placeholder"
               :name="vFilter.id" :id="vFilter.id"
               v-model="vFilter.value" @input="filtersChanged">
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
