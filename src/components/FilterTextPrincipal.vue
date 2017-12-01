<template>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <label :for="vFilter.id"
                   v-if="vFilter.label">{{vFilter.label}}</label>

            <div :class="'input-group filter filter-text filter-main ' + vFilter.class"
                 :id="'filter-' + vFilter.count">
                <input type="text" class="form-control block-level"
                       :placeholder="vFilter.placeholder" :id="vFilter.id" :name="vFilter.id"
                       v-model="vFilter.value" @input="filtersChanged">
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
                    <input type="checkbox" name="exactSearch" id="exactSearch"
                           v-model="vFilter.isExactSearch" @change="filtersChanged">{{vFilter.labelExactResult}}
                </label>
            </div>
        </div>
    </div>
</template>

<script>
    import {events} from '../main'

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
                events.$emit('filtersChanged');
            }
        }
    }
</script>

<style>
</style>
