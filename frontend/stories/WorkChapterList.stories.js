import React from "react";
import WorkChapterList from "./WorkChapterList";

export default {
  title: "WorkChapterList",
  component: WorkChapterList,
};

const Template = (args) => <WorkChapterList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  chapters: [
    {
      id: 1,
      title: "Of Stocks and Demons",
      date: "9/4/2022, 12:00 PM",
    },
    {
      id: 2,
      title: "Born to be rich",
      date: "9/5/2022, 7:26 PM",
    },
    {
      id: 4,
      title: "Ash in a broken world",
      date: "9/7/2022, 8:00 AM",
    },
    {
      id: 3,
      title: "Queen",
      date: "9/6/2022, 4:15 PM",
    },
  ],
};
