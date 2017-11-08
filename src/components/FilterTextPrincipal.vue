<template>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <label :for="filter.id"
                   v-if="filter.label">{{filter.label}}</label>

            <div :class="'input-group filter filter-text filter-main ' + filter.class"
                 :id="'filter-' + filter.count">
                <input type="text" class="form-control block-level"
                       :placeholder="filter.placeholder" :id="filter.id" :name="filter.id"
                       v-model="filter.value" @change="changeFilter">
                <span class="input-group-btn">
                    <button type="button" name="submit"
                            :value="filter.buttonText" :class="'btn ' + filter.buttonClass">
                        {{filter.buttonText}}
                    </button>
                </span>
            </div>

            <div class="checkbox"
                 v-if="filter.showExactSearch">
                <label for="exactSearch" class="exact-search">
                    <input type="checkbox" name="exactSearch" id="exactSearch">{{filter.labelExactResult}}
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
            filter:{
                type: Object,
                required: true
            }
        },
        methods: {
            changeFilter(){
                console.log(Filters, '$emit changeFilter',this.filter);
//                Filters.emit('changeFilter', this.filter);
            }
        }
    }
</script>

<style>
</style>
