<template>
    <div :class="'form-group filter filter-text ' + vFilter.class" :id="'filter-' + vFilter.count">
        <label :for="vFilter.id"
               v-if="vFilter.label">{{vFilter.label}}</label>
        <label :for="vFilter.id" class="sr-only"
               v-else>{{vFilter.placeholder}}</label>
        <input type="text" class="form-control" :placeholder="vFilter.placeholder"
               :name="vFilter.id" :id="vFilter.id"
               v-model="vFilter.value">
    </div>
</template>

<script>
    export default
    {
        props:{
            vFilter:{
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
