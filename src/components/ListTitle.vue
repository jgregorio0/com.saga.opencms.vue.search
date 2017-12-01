<template>
    <div class="col-lg-12 nopadding list list-title">
        <dl>
            <dt>ID</dt>
            <dd>{{ vData.id}}</dd>
        </dl>
        <dl>
            <dt>PATH</dt>
            <dd>{{ vData.path}}</dd>
        </dl>
        <dl>
            <dt>TITLE</dt>
            <dd>{{ vData.Title_prop}}</dd>
        </dl>
    </div>
</template>

<script>
    export default
    {
        props:{
            vData:{
                type: Object,
                required: true
            }
        }
    }
</script>

<style>
</style>
