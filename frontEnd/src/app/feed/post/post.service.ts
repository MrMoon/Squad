import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }
  getPostsByGroupID(ID){
  
    if(ID==1){     
      console.log("test");
  return this.getPosts();
}

return ;
    
  
  }
  getPosts(){
    return[];
  }
}
