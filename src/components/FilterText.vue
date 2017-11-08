<template>
    <div :class="'form-group filter filter-text ' + filter.class" :id="'filter-' + filter.count">
        <label :for="filter.id"
               v-if="filter.label">{{filter.label}}</label>
        <label :for="filter.id" class="sr-only"
               v-else>{{filter.placeholder}}</label>
        <input type="text" class="form-control" :placeholder="filter.placeholder"
               :name="filter.id" :id="filter.id"
               v-model:value="filter.value">
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
