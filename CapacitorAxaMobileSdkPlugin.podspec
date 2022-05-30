require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorAxaMobileSdkPlugin'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
  s.ios.deployment_target  = '12.0'
  s.dependency 'Capacitor'
  
  s.dependency 'CAMobileAppAnalytics'
  s.script_phases = [
      {   :name => 'Precompile',
         :script => 'cd "${PODS_ROOT}"
                    irb <<EOF
                    require "xcodeproj"

project_path = "Pods.xcodeproj/"
project = Xcodeproj::Project.open(project_path)
project.targets.each do |target|
    if target.name == "CapacitorAxaMobileSdkPlugin" && !(target.headers_build_phase.file_display_names.include? "CAMDOReporter.h")
        group = project.main_group.find_subpath(File.join("Pods"),true )
        file_ref=group.new_file("Headers/Public/CAMobileAppAnalytics/CAMDOReporter.h")
        build_file = target.headers_build_phase.add_file_reference (file_ref)
        build_file.settings = { "ATTRIBUTES" => ["Public"] }
        puts "Done the updatation"
        project.save
    end
end
                    EOF
                    cd - ',
         :execution_position => :before_compile
       }
  ]
  s.swift_version = '5.1'
  s.static_framework = true
end
