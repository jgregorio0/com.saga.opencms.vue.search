import Vue from 'vue'
import Filters from './Filters.vue'
import Results from './Results.vue'

export const filters = new Vue({
  el: 'v-filters',
  render(h) {
    return h(Filters, {
      props: {
        id: this.$el.attributes.id.value + "-filters",
        locale: this.$el.attributes.locale.value,
        filters: JSON.parse(this.$el.attributes.filters.value)
        //,
        //start: Number(this.$el.attributes.start.value),
        //total: 0,
        //datasSize: 0,
        //datas: [],
        //fields: [],
        //loading: false
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
        locale: this.$el.attributes.locale.value,
        rows: Number(this.$el.attributes.rows.value),
        start: Number(this.$el.attributes.start.value),
        controller: this.$el.attributes.controller.value,
        query: this.$el.attributes.query.value
        //,
        //start: Number(this.$el.attributes.start.value),
        //total: 0,
        //datasSize: 0,
        //datas: [],
        //fields: [],
        //loading: false
      }
    })
  }
})

/*
new Vue({
  el: 'v-searcher',
  render(h) {
    return h(App, {
      props: {
        id: this.$el.attributes.id.value + "-results",
        controller: this.$el.attributes.controller.value,
        query: this.$el.attributes.query.value,
        filters: JSON.parse(this.$el.attributes.filters.value),
        showFilters: this.$el.attributes.showFilters.value === 'true',
        locale: this.$el.attributes.locale.value,
        rows: Number(this.$el.attributes.rows.value)
        //,
        //start: Number(this.$el.attributes.start.value),
        //total: 0,
        //datasSize: 0,
        //datas: [],
        //fields: [],
        //loading: false
      }
    })
  }
})*/
