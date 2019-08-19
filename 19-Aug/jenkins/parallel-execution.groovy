node{
    
    stage('Parallel'){
        
        parallel taskOne: {
            for(int i=0; i<=5; i++){
                echo "task-1iteration-$i"
                sleep 2
            }
        }, taskTwo: {
            for(int i=0; i<=5; i++){
                echo "task-2iteration-$i"
                sleep 2
            }
        },
        failFast: fase
            
        }
}
