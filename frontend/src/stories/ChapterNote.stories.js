import React from "react";
import ChapterNote from "./ChapterNote";

export default {
  title: "ChapterNote",
  component: ChapterNote,
  argTypes: {
    onNext: { action: "The Next is the Best" },
  },
};

const Template = (args) => <ChapterNote {...args} />;

export const Visible = Template.bind({});
Visible.args = {
  text: "This is header text",
  isVisible: true,
};

export const NotVisible = Template.bind({});
NotVisible.args = {
  text: "This is header text",
  isVisible: "hidden",
};
