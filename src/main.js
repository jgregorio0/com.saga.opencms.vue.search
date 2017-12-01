import Vue from "vue";
import VueResource from 'vue-resource'
import Filters from "./Filters.vue";
import Results from "./Results.vue";

Vue.use(VueResource);

export const events = new Vue();

export const filters = new Vue({
    el: 'v-filters',
    render(h) {
        return h(Filters, {
            props: {
                id: this.$el.attributes.id.value + "-filters",
                pLocale: this.$el.attributes.pLocale.value,
                pFilters: JSON.parse(this.$el.attributes.pFilters.value)
            }
        })
    }
})

export const results = new Vue({
    el: 'v-results',
    render(h) {
        return h(Results, {
            props: {
                id: this.$el.attributes.id.value + "-results",
                pLocale: this.$el.attributes.pLocale.value,
                pRows: Number(this.$el.attributes.pRows.value),
                pStart: Number(this.$el.attributes.pStart.value),
                pController: this.$el.attributes.pController.value,
                pQuery: this.$el.attributes.pQuery.value
            }
        })
    }
})