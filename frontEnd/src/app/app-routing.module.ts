import { EmptyComponent } from './empty/empty.component';
import { FeedComponent } from './feed/feed.component';
import { ChatComponent } from './chat/chat.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrganizerComponent } from './organizer/organizer.component';

const routes: Routes = [
  {
    path:'organizer/:noteID',component:OrganizerComponent ,outlet:'one'
  } , {
    path:'feed/:groupID',component:FeedComponent ,outlet:'one'
  } ,
  {
    path:'feed/:groupID',component:FeedComponent ,outlet:'two'
  },
  {
    path:'chat/:roomID',component:ChatComponent ,outlet:'one'
  } ,
  {
    path:'chat/:roomID',component:ChatComponent ,outlet:'two'
  } ,  {
    path:'empty',component:EmptyComponent ,outlet:'two'
  } ,  {
    path:'empty',component:EmptyComponent ,outlet:'one'
  } 

  // {
  //   path:'ex',component:ExperiencesComponent ,outlet:'one'
  // },
  // {
  //   path:'ac',component:AchievementsComponent ,outlet:'two'
  // }, 
  // {
  //   path:'ex',component:ExperiencesComponent ,outlet:'two'
  // }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
