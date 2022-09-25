<template>
  <section class="elk">
      <div class="search">
        <h2> Query Builder</h2>
          <b-field label="Key"
          type="is-success"
         >
          <b-input v-model="logKey" maxlength="30"  placeholder="Search for keys..."></b-input>
        </b-field>
        <b-field label="Value"
        type="is-success"
        >
        <b-input v-model="logValue" maxlength="30" placeholder="Enter value..."></b-input>
        </b-field>
        <b-field label="Number"
            type="is-success"
            >
            <b-input v-model="logNumber" maxlength="30" placeholder="Number of logs..."></b-input>
        </b-field>
        <b-button class="searchButton" @click="fetchSomething"> Search</b-button>
    </div>
    <div class="results">
        <h2> Results </h2>
        <ul>
            <li class="logs" v-for=" log,index in logs" 
            :key="index">
            <p>Log No #{{index + 1}}</p>
            <div class="logButtons">
                <b-button
                    @click="isCardModalActive = !isCardModelActive" type="is-white" size="is-small"
                    icon-left="eye">
                    View Log
                </b-button>
                <b-button
                @click="copyURL(log)" type="is-white" size="is-small"
                    icon-left="file-multiple">
                    Copy Log
                </b-button>
                </div>
                <div class="log">
                 {{log}}
                </div>
            <b-modal v-model="isCardModalActive" @click="setCurrentLog(index)" :width="640" scroll="keep">
                <div class="isModal">
                    {{log}}
                </div>        
            </b-modal>
            <!-- <b-table
                    :data="data">
                    <template v-for="column in columns">
                        <b-table-column :key="column.id" v-bind="column" sortable>
                            <template
                                v-if="column.searchable && !column.numeric"
                                #searchable="props">
                                <b-input
                                    v-model="props.filters[props.column.field]"
                                    placeholder="Search..."
                                    icon="magnify"
                                    size="is-small" />
                            </template>
                            <template v-slot="props">
                                {{ props.row[column.field] }}
                            </template>
                        </b-table-column>
                    </template>
                </b-table> -->
            </li>
        </ul>
    </div>
   
  </section>
</template>

<script>

  export default {
      data() {
          return {
              logs: '',
              logKey: '@timestamp',
              logValue: '2022-09-04T22:46:36.062Z',
              logNumber: 10,
              isCardModalActive: false,
              currentLog: '',
              logsTry: ' '
          }

      },
  methods: {
    async copyURL(mytext) {
    try {
      await navigator.clipboard.writeText(mytext);
      alert('Copied');
    } catch($e) {
      alert('Cannot copy');
    }
  },
    setCurrentLog(){
        this.currentLog = this.logs[index];
    },
    async fetchSomething() {
        console.log(this);
        const ip = await this.$axios.$post('http://192.168.2.101:8080/get',{
            indexName :this.logKey,
            indexValue: this.logValue,
            logNumber: this.logNumber
        }).then((response)=> {
            console.log(response);
            this.logs = response;
        });
    },
  }
}

</script>
<style >
    body{
        background-color: white;
    }
    .elk {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        gap: 100px;
    }
    .logs {
        height: 300px;
        overflow: scroll;
        display: flex;
        
    }
    li {
        margin-bottom: 2rem;
    }
    p {
        border: 1px solid blue;
        color: blue;

    }
    .label {
        color: blue;
    }
    h2{ 
        font-size: larger;
        color: blue;
        margin-bottom: 2rem;
    }
    .search {
        width:25%;
        margin-top: 2rem;
        margin-bottom: 2rem;
        padding: 1rem;
        border: 2px solid black;
        height: 500px;
    }
    .modal-stuff {
        color: blue;
    }
    .logBUttons {
        display: flex;
        flex-direction: column;
        height: 100%;
    }
    .button {
        height: 50%;
        border: 1px solid white;
    }
    .results {
        width:70%;
    }
    .searchButton {
        height: 50px;
    }
    .isModal {
        background-color: white;
        color: blue;

    }
    .log {
        background-color: white;
        color: black;
    }
</style>