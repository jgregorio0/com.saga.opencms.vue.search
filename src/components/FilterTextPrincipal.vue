<template>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <label :for="vFilter.id"
                   v-if="vFilter.label">{{vFilter.label}}</label>

            <div :class="'input-group filter filter-text filter-main ' + vFilter.class"
                 :id="'filter-' + vFilter.count">
                <input type="text" class="form-control block-level"
                       :placeholder="vFilter.placeholder" :id="vFilter.id" :name="vFilter.id"
                       v-model="vFilter.value" @change="changeFilter">
                <span class="input-group-btn">
                    <button type="button" name="submit"
                            :value="vFilter.buttonText" :class="'btn ' + vFilter.buttonClass">
                        {{vFilter.buttonText}}
                    </button>
                </span>
            </div>

            <div class="checkbox"
                 v-if="vFilter.showExactSearch">
                <label for="exactSearch" class="exact-search">
                    <input type="checkbox" name="exactSearch" id="exactSearch">{{vFilter.labelExactResult}}
                </label>
            </div>
        </div>
    </div>
</template>

<script>
    import Filters from '../Filters.vue'

    export default
    {
        props:{
            vFilter:{
                type: Object,
                required: true
            }
        },
        methods: {
            changeFilter(){
                console.log('$emit changeFilters', Filters);
                filters.$emit('changeFilters', Filters);
            }
        }
    }
</script>

<style>
</style>
